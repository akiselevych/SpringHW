package com.toni.homeworkproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Account {
    private Long id;
    private String accountNumber;
    private Currency currency;
    private BigDecimal balance;
    private Long customerId;

    public Account(Currency currency, Long customerId) {
        this.currency = currency;
        this.customerId = customerId;
        this.accountNumber = UUID.randomUUID().toString();
        this.balance = new BigDecimal(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
