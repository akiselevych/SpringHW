package com.toni.homeworkproject.facade.customer;

import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.dtos.request.CustomerRequestDto;
import com.toni.homeworkproject.domain.dtos.response.CustomerResponseDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CustomerResponseMapper extends DtoMapperFacade<Customer, CustomerResponseDto> {
    public CustomerResponseMapper() {
        super(Customer.class, CustomerResponseDto.class);
    }

    @Override
    protected void decorateDto(CustomerResponseDto dto, Customer entity) {
        if (entity.getEmployers() == null){
            dto.setEmployers(new ArrayList<>());
        }
        if (entity.getAccounts() == null){
            dto.setAccounts(new HashSet<>());
        }
    }
}
