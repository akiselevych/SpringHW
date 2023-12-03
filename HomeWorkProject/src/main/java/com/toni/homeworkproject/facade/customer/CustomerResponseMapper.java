package com.toni.homeworkproject.facade.customer;

import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.dtos.request.CustomerRequestDto;
import com.toni.homeworkproject.domain.dtos.response.CustomerResponseDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import com.toni.homeworkproject.facade.employer.EmployerResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerResponseMapper extends DtoMapperFacade<Customer, CustomerResponseDto> {

    private final EmployerResponseMapper employerResponseMapper;
    public CustomerResponseMapper(EmployerResponseMapper employerResponseMapper) {
        super(Customer.class, CustomerResponseDto.class);
        this.employerResponseMapper = employerResponseMapper;
    }

    @Override
    protected void decorateDto(CustomerResponseDto dto, Customer entity) {
//        dto.setEmployers(entity.getEmployers().stream().map(employerResponseMapper::convertToDto).toList());
        if (entity.getEmployers() == null){
            dto.setEmployers(List.of());
        }
        if (entity.getAccounts() == null){
            dto.setAccounts(Set.of());
        }
    }
}
