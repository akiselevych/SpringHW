package com.toni.homeworkproject.resource;

import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.dtos.request.CustomerRequestDto;
import com.toni.homeworkproject.facade.account.AccountRequestMapper;
import com.toni.homeworkproject.facade.account.AccountResponseMapper;
import com.toni.homeworkproject.facade.customer.CustomerRequestMapper;
import com.toni.homeworkproject.facade.customer.CustomerResponseMapper;
import com.toni.homeworkproject.service.DefaultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "https://client-for-java.vercel.app", allowCredentials = "true")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final DefaultService<Customer> customerService;
    private final DefaultService<Account> accountService;
    private final DefaultService<Employer> employerService;
    private final CustomerResponseMapper responseCustomerMapper;
    private final CustomerRequestMapper requestCustomerMapper;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(customerService.findAll().stream().map(responseCustomerMapper::convertToDto).toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()){
            return ResponseEntity.ok(responseCustomerMapper.convertToDto(customerOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CustomerRequestDto customerDto){
        Customer newCustomer = requestCustomerMapper.convertToEntity(customerDto);
        return ResponseEntity.ok(responseCustomerMapper.convertToDto(customerService.create(newCustomer)));
    }

    @PostMapping("/{customerId}/linked-with-employer/{employerId}")
    public ResponseEntity<?> addEmployer(@PathVariable(name = "customerId") Long customerId,
                                         @PathVariable(name = "employerId") Long employerId){
        Optional<Customer> customerOpt = customerService.findById(customerId);
        Optional<Employer> employerOpt = employerService.findById(employerId);
        if (customerOpt.isPresent() && employerOpt.isPresent()){
            Customer customer = customerOpt.get();
            Employer employer = employerOpt.get();
            customer.getEmployers().add(employer);
            customerService.update(customer);
            return ResponseEntity.status(201).body(responseCustomerMapper.convertToDto(customer));
        } else {
            return ResponseEntity.status(404).body("No such customer or employer with this ID");
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Customer customer){
        return ResponseEntity.ok().body(responseCustomerMapper.convertToDto(customerService.update(customer)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        customerService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return ResponseEntity.status(400).body(getErrorsMap(errors));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}
