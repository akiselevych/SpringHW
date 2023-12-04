package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.CustomerJpaRepository;
import com.toni.homeworkproject.domain.Customer;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerJpaRepository customerJpaRepository;

    @Captor
    private ArgumentCaptor<Customer> argumentCaptor;
    @InjectMocks
    private CustomerService customerService;

    @Test
    public void paginationAndSortFindAllTest(){
        Customer customer = new Customer();
        customer.setId(1L);
        Customer customer2 = new Customer();
        customer2.setId(3L);
        Customer customer3 = new Customer();
        customer3.setId(5L);
        Customer customer4 = new Customer();
        customer4.setId(2L);
        when(customerJpaRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(customer,customer4,customer2)));
        List<Customer> customers = customerService.findAll(0,3);
        assertEquals(List.of(customer,customer4,customer2),customers);
    }

    @Test
    public void findByExistingIdTest(){
        Customer customer = new Customer();
        customer.setId(4L);

        when(customerJpaRepository.findById(4L)).thenReturn(Optional.of(customer));

        Optional<Customer> customerOptional = customerService.findById(4L);

        assertEquals(customer, customerOptional.get());
    }

    @Test
    public void findByNotExistingIdTest(){

        when(customerJpaRepository.findById(4L)).thenReturn(Optional.empty());

        Optional<Customer> customerOptional = customerService.findById(4L);

        assertThrows(NoSuchElementException.class, customerOptional::get);
    }

    @Test
    public void findByNullIdTest(){

        when(customerJpaRepository.findById(null)).thenReturn(Optional.empty());

        Optional<Customer> customerOptional = customerService.findById(null);

        assertThrows(NoSuchElementException.class, customerOptional::get);
    }

    @Test
    public void createNewCustomerTest(){
        Customer customer = new Customer();
        when(customerJpaRepository.save(customer)).thenReturn(customer);
        Customer createdCustomer = customerService.create(customer);

        assertEquals(createdCustomer,customer);
    }

    @Test
    public void createNewCustomerCaptorTest(){
        Customer customer = new Customer();
        customerService.create(customer);

        verify(customerJpaRepository).save(argumentCaptor.capture());

        Customer captedCustomer = argumentCaptor.getValue();
        assertEquals(customer,captedCustomer);
    }

    @Test
    public void createNullCustomerTest(){
        when(customerJpaRepository.save(null)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class,() -> customerService.create(null));
    }

    @Test
    public void updateCustomerTest(){
        Customer customer = new Customer();

        when(customerJpaRepository.save(customer)).thenReturn(customer);

        assertEquals(customer,customerService.update(customer));
    }
}
