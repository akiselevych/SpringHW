package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.facade.account.AccountRequestMapper;
import com.toni.homeworkproject.facade.account.AccountResponseMapper;
import com.toni.homeworkproject.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(
        origins = {
                "https://client-for-java.vercel.app"
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
public class AccountsController {
    private final DefaultService<Account> accountService;
    private final DefaultService<Customer> customerService;
    private final AccountRequestMapper requestAccountMapper;
    private final AccountResponseMapper responseAccountMapper;

    @PostMapping("/{id}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable(name = "id") Long id, @RequestBody Currency currency){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            Account newAccount = accountService.create(new Account(currency,customer.get()));
            return ResponseEntity.status(201).body(responseAccountMapper.convertToDto(newAccount));
        } else {
            return ResponseEntity.status(404).body("No such customer with this ID");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "id") Long id){
        accountService.delete(id);
        return ResponseEntity.status(204).build();
    }


    @PostMapping("/replenish/{accountNumber}/{sum}")
    public ResponseEntity<?> replenishAccount(@PathVariable(name = "accountNumber") String accountNumber, @PathVariable(name = "sum") BigDecimal sum) {
        Optional<Account> accountOptional = accountService.findAll().stream()
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
        return ResponseEntity.ok().build();
    }

    @PostMapping("/withdraw/{accountNumber}/{sum}")
    public ResponseEntity<?> withdrawAccount(@PathVariable(name = "accountNumber") String accountNumber,
                                             @PathVariable(name = "sum") BigDecimal sum) {
        Optional<Account> accountOptional = accountService.findAll().stream()
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
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send/{senderAccNo}/{receiverAccNo}/{sum}")
    public ResponseEntity<?> sendToAnotherAccount(@PathVariable(name = "senderAccNo") String senderAccNumber,
                                                  @PathVariable(name = "receiverAccNo") String receiverAccNumber,
                                                  @PathVariable(name = "sum") BigDecimal sum) {
        List<Account> accounts = accountService.findAll();
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
        return ResponseEntity.ok().build();
    }
}
