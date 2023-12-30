package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.AccountMessageType;
import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.dtos.response.MessageAccountResponseDto;
import com.toni.homeworkproject.facade.account.AccountRequestMapper;
import com.toni.homeworkproject.facade.account.AccountResponseMapper;
import com.toni.homeworkproject.facade.account.MessageAccountResponseMapper;
import com.toni.homeworkproject.service.DefaultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(
        origins = {
                "http://localhost:5173"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Slf4j
public class AccountsController {
    private final DefaultService<Account> accountService;
    private final DefaultService<Customer> customerService;
    private final AccountRequestMapper requestAccountMapper;
    private final AccountResponseMapper responseAccountMapper;
    private final MessageAccountResponseMapper messageAccountMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/{id}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable(name = "id") Long id, @RequestBody Currency currency) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            Account newAccount = accountService.create(new Account(currency, customer.get()));
            log.info("Account created for user with ID" + customer.get().getId());

            MessageAccountResponseDto response = messageAccountMapper.convertToDto(newAccount);
            response.setType(AccountMessageType.CREATE);
            messagingTemplate.convertAndSend("/topic/accounts", List.of(response));
            return ResponseEntity.status(201).body(responseAccountMapper.convertToDto(newAccount));
        } else {
            return ResponseEntity.status(404).body("No such customer with this ID");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") Long id) {
        log.info("Account with ID: " + id + "deleted");
        accountService.delete(id);

        MessageAccountResponseDto response = new MessageAccountResponseDto();
        response.setType(AccountMessageType.CREATE);
        response.setId(id);
        messagingTemplate.convertAndSend("/topic/accounts", List.of(response));

        return ResponseEntity.status(204).build();
    }


    @PostMapping("/replenish/{accountNumber}/{sum}")
    public ResponseEntity<?> replenishAccount(@PathVariable(name = "accountNumber") String accountNumber, @PathVariable(name = "sum") BigDecimal sum) {
        Optional<Account> accountOptional = accountService.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber)).findAny();
        if (sum.compareTo(BigDecimal.valueOf(0)) < 0) {
            return ResponseEntity.badRequest().body("Sum must be more than 0");
        }
        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(404).body("No such account with this number");
        }
        Account account = accountOptional.get();
        account.setBalance(account.getBalance().add(sum));
        accountService.update(account);
        log.info("Account " + accountNumber + "replenished");



        messagingTemplate.convertAndSend("/topic/accounts", List.of(account));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw/{accountNumber}/{sum}")
    public ResponseEntity<?> withdrawAccount(@PathVariable(name = "accountNumber") String accountNumber,
                                             @PathVariable(name = "sum") BigDecimal sum) {
        Optional<Account> accountOptional = accountService.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber)).findAny();
        if (sum.compareTo(BigDecimal.valueOf(0)) < 0) {
            return ResponseEntity.badRequest().body("Sum must be more than 0");
        }
        if (accountOptional.isEmpty()) {
            return ResponseEntity.status(404).body("No such account with this number");
        }
        Account account = accountOptional.get();
        if (account.getBalance().compareTo(sum) < 0) {
            return ResponseEntity.status(404).body("Not enough money on account");
        }
        account.setBalance(account.getBalance().subtract(sum));
        accountService.update(account);
        log.info("Withdraw succeed");

        MessageAccountResponseDto response = messageAccountMapper.convertToDto(account);
        response.setType(AccountMessageType.UPDATE);
        messagingTemplate.convertAndSend("/topic/accounts", List.of(response));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/send/{senderAccNo}/{receiverAccNo}/{sum}")
    public ResponseEntity<?> sendToAnotherAccount(@PathVariable(name = "senderAccNo") String senderAccNumber,
                                                  @PathVariable(name = "receiverAccNo") String receiverAccNumber,
                                                  @PathVariable(name = "sum") BigDecimal sum) {
        List<Account> accounts = accountService.findAll(Sort.by(Sort.Direction.ASC, "id"));
        Optional<Account> senderOptional = accounts.stream().filter(acc -> acc.getAccountNumber().equals(senderAccNumber)).findAny();
        Optional<Account> receiverOptional = accounts.stream().filter(acc -> acc.getAccountNumber().equals(receiverAccNumber)).findAny();
        if (sum.compareTo(BigDecimal.valueOf(0)) < 0) {
            return ResponseEntity.badRequest().body("Sum must be more than 0");
        }
        if (senderOptional.isEmpty() || receiverOptional.isEmpty()) {
            return ResponseEntity.status(404).body("No such account with this number");
        }
        Account sender = senderOptional.get();
        Account receiver = receiverOptional.get();
        if (sender.getBalance().compareTo(sum) < 0) {
            return ResponseEntity.status(404).body("Not enough money on account");
        }
        sender.setBalance(sender.getBalance().subtract(sum));
        receiver.setBalance(receiver.getBalance().add(sum));
        accountService.update(sender);
        accountService.update(receiver);
        log.info("Money was sent");


        MessageAccountResponseDto responseSender = messageAccountMapper.convertToDto(sender);
        responseSender.setType(AccountMessageType.UPDATE);

        MessageAccountResponseDto responseReceiver = messageAccountMapper.convertToDto(receiver);
        responseReceiver.setType(AccountMessageType.UPDATE);

        messagingTemplate.convertAndSend("/topic/accounts", List.of(responseReceiver,responseReceiver));

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptions(Exception e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
