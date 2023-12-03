package com.toni.homeworkproject.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.toni.homeworkproject.domain.Customer;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customer")
@JsonView({CustomerResponseDtoView.Single.class, CustomerResponseDtoView.Many.class})
public class AccountResponseDto {
    private Long id;
    private String accountNumber;
    private CustomerResponseDto customer;
    private BigDecimal balance;
}
