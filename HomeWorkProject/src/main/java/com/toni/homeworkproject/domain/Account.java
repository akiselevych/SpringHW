package com.toni.homeworkproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "customer")
@EqualsAndHashCode(of = "id", callSuper = true)
public class Account extends AbstractEntity {
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(name = "balance")
    private BigDecimal balance;

    @JsonBackReference
    @ManyToOne
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this.currency = currency;
        this.customer = customer;
        this.accountNumber = UUID.randomUUID().toString();
        this.balance = new BigDecimal(0);
    }

}
