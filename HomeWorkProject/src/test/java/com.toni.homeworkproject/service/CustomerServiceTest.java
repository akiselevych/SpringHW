package com.toni.homeworkproject.service;

import com.toni.homeworkproject.dao.CustomerJpaRepository;
import com.toni.homeworkproject.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerJpaRepository customerJpaRepository;
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

        assertEquals(List.of(customer,customer4,customer2),customerService.findAll(0,3));
    }
}
