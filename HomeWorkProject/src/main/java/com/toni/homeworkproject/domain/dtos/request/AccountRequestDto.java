package com.toni.homeworkproject.domain.dtos.request;

import com.toni.homeworkproject.domain.Currency;
import com.toni.homeworkproject.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    private Currency currency;
    private CustomerRequestDto customer;
}
