package com.toni.homeworkproject.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.toni.homeworkproject.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    @JsonBackReference
    private CustomerResponseDto customer;
    private BigDecimal balance;
}
