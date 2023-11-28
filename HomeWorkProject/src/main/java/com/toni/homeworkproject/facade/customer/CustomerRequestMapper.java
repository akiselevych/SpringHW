package com.toni.homeworkproject.facade.customer;

import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.dtos.request.CustomerRequestDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class CustomerRequestMapper extends DtoMapperFacade<Customer,CustomerRequestDto> {
    public CustomerRequestMapper() {
        super(Customer.class, CustomerRequestDto.class);
    }

}
