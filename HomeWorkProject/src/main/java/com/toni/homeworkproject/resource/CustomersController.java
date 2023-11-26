package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.service.DefaultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "https://client-for-java.vercel.app", allowCredentials = "true")
@RestController
@RequestMapping("/customers")
public class CustomersController {
    private final DefaultService<Customer> customerService;
    private final DefaultService<Account> accountService;

    public CustomersController(DefaultService<Customer> customerService, DefaultService<Account> accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(customerService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        return ResponseEntity.of(customerService.findById(id));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Customer customer){
        Customer newCustomer = customerService.create(new Customer(customer.getName(), customer.getSurname(),
                                                                    customer.getEmail(), customer.getAge()));
        return ResponseEntity.status(201).body(newCustomer);
    }
    @PostMapping("/{id}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable(name = "id") Long id, @RequestBody Currency currency){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            Account newAccount = accountService.create(new Account(currency,customer.get()));
            return ResponseEntity.status(201).body(newAccount);
        } else {
            return ResponseEntity.status(404).body("No such customer with this ID");
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Customer customer){
        return ResponseEntity.ok().body(customerService.update(customer));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        customerService.delete(id);
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "customerId") Long customerId ,
                                           @PathVariable(name = "accountId") Long accountId){
        customerService.findById(customerId).get().getAccounts().remove(accountService.findById(accountId).get());
        accountService.delete(accountId);
        return ResponseEntity.status(204).build();
    }
}
