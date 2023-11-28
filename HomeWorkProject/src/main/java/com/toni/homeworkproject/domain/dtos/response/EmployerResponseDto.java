package com.toni.homeworkproject.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toni.homeworkproject.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerResponseDto {
    private Long id;
    private String name;
    private String address;
    @JsonBackReference
    private Set<Customer> customers;
}
