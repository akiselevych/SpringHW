package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.service.DefaultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(
        origins = {
                "http://localhost:3000"
        },
        methods = {
                RequestMethod.OPTIONS,
                RequestMethod.GET,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.POST
        })
@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private final DefaultService<Account> accountService;

    public AccountsController(DefaultService<Account> accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/replenish")
    public ResponseEntity<?> replenishAccount(@RequestBody Map<String,String> data) {
        BigDecimal sum = new BigDecimal(data.get("sum"));
        List<Account> accounts = accountService.findAll();
        Optional<Account> accountOptional = accounts.stream().filter(acc -> acc.getAccountNumber().equals(data.get("accNum"))).findAny();
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

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdrawAccount(@RequestBody Map<String,String> data) {
        BigDecimal sum = new BigDecimal(data.get("sum"));
        List<Account> accounts = accountService.findAll();
        Optional<Account> accountOptional = accounts.stream().filter(acc -> acc.getAccountNumber().equals(data.get("accNum"))).findAny();
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

    @PostMapping("/send")
    public ResponseEntity<?> sendToAnotherAccount(@RequestBody Map<String, String> data) {
        BigDecimal sum = new BigDecimal(data.get("sum"));
        List<Account> accounts = accountService.findAll();
        Optional<Account> senderOptional = accounts.stream().filter(acc -> acc.getAccountNumber().equals(data.get("senderAccNum"))).findAny();
        Optional<Account> receiverOptional = accounts.stream().filter(acc -> acc.getAccountNumber().equals(data.get("receiverAccNum"))).findAny();
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
        return ResponseEntity.ok().build();
    }
}
