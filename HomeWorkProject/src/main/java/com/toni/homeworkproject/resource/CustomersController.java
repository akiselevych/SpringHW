package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "https://client-for-java.vercel.app", allowCredentials = "true")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final DefaultService<Customer> customerService;
    private final DefaultService<Account> accountService;
    private final DefaultService<Employer> employerService;


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
    @PostMapping("/{customerId}/employers/{employerId}")
    public ResponseEntity<?> addEmployer(@PathVariable(name = "customerId") Long customerId,
                                         @PathVariable(name = "employerId") Long employerId){
        Optional<Customer> customerOpt = customerService.findById(customerId);
        Optional<Employer> employerOpt = employerService.findById(employerId);
        if (customerOpt.isPresent() && employerOpt.isPresent()){
            Customer customer = customerOpt.get();
            Employer employer = employerOpt.get();
            customer.getEmployers().add(employer);
            customerService.update(customer);
            return ResponseEntity.status(201).body(customer);
        } else {
            return ResponseEntity.status(404).body("No such customer or employer with this ID");
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
