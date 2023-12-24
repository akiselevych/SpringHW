package com.toni.homeworkproject.domain.dtos.response;

import com.toni.homeworkproject.domain.AccountMessageType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customer")
public class MessageAccountResponseDto {
    private Long id;
    private String accountNumber;
    private CustomerResponseDto customer;
    private BigDecimal balance;
    private AccountMessageType type;
}
