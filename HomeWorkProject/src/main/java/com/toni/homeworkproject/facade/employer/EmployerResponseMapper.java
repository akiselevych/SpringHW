package com.toni.homeworkproject.facade.employer;

import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.dtos.response.EmployerResponseDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import com.toni.homeworkproject.facade.customer.CustomerResponseMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployerResponseMapper extends DtoMapperFacade<Employer, EmployerResponseDto> {
    public EmployerResponseMapper() {
        super(Employer.class, EmployerResponseDto.class);
    }

    @Override
    protected void decorateDto(EmployerResponseDto dto, Employer entity) {
        if (entity.getCustomers() == null){
            dto.setCustomers(Set.of());
        }
    }
}
