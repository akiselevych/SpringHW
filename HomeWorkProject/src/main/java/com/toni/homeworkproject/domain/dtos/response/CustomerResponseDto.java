package com.toni.homeworkproject.domain.dtos.response;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Employer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    private String phone;
    @JsonManagedReference
    private Set<AccountResponseDto> accounts;
    @JsonManagedReference
    private List<EmployerResponseDto> employers;
}
