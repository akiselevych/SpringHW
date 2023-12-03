package com.toni.homeworkproject.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.dtos.request.CustomerRequestDto;
import com.toni.homeworkproject.domain.dtos.response.CustomerResponseDto;
import com.toni.homeworkproject.domain.dtos.response.CustomerResponseDtoView;
import com.toni.homeworkproject.facade.customer.CustomerRequestMapper;
import com.toni.homeworkproject.facade.customer.CustomerResponseMapper;
import com.toni.homeworkproject.service.DefaultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
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

//    @GetMapping
//    public ResponseEntity<?> findAll(){
//        List<Customer> customers = customerService.findAll(Sort.by(Sort.Direction.ASC,"id"));
//        List<CustomerResponseDto> customersResponse = customers.stream().map(responseCustomerMapper::convertToDto).toList();
//        return ResponseEntity.ok().body(customersResponse);
//    }

    @GetMapping
    @JsonView(CustomerResponseDtoView.Many.class)
    public ResponseEntity<?> findAll(@RequestParam(name = "page") Integer page,
                                     @RequestParam(name = "quantity",required = false) Integer quantity){
        List<Customer> customers = customerService.findAll(page,quantity == null ? 10 : quantity);
        List<CustomerResponseDto> customersResponse = customers.stream().map(responseCustomerMapper::convertToDto).toList();
        return ResponseEntity.ok().body(customersResponse);
    }

    @GetMapping("/{id}")
    @JsonView(CustomerResponseDtoView.Single.class)
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()){
            CustomerResponseDto res = responseCustomerMapper.convertToDto(customerOptional.get());
            return ResponseEntity.ok().body(res);
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

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody Map<Object,Object> fields){
        Optional<Customer> customerOptional = customerService.findById(id);
        if (customerOptional.isPresent()){
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Customer.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, customerOptional.get(), value);
                }
            });
            return ResponseEntity.ok().body(responseCustomerMapper.convertToDto(customerService.update(customerOptional.get())));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
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
