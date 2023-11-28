package com.toni.homeworkproject.facade.employer;

import com.toni.homeworkproject.domain.Employer;
import com.toni.homeworkproject.domain.dtos.request.EmployerRequestDto;
import com.toni.homeworkproject.facade.DtoMapperFacade;
import org.springframework.stereotype.Service;

@Service
public class EmployerRequestMapper  extends DtoMapperFacade<Employer, EmployerRequestDto> {
    public EmployerRequestMapper() {
        super(Employer.class, EmployerRequestDto.class);
    }
}
