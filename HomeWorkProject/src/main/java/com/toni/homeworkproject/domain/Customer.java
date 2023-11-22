package com.toni.homeworkproject.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer age;
    @JsonManagedReference
    private List<Account> accounts;

    public Customer(String name, String surname, String email, Integer age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.accounts = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
