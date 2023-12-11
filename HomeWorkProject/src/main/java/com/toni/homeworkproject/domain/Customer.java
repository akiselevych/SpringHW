package com.toni.homeworkproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = true)
@NamedEntityGraph(name = "customerWithAccountsAndEmployers",
        attributeNodes = {
                @NamedAttributeNode("accounts"),
                @NamedAttributeNode(value = "employers", subgraph = "employers"),
                @NamedAttributeNode(value = "roles",subgraph = "roles")
        },
        subgraphs = {
                @NamedSubgraph(name = "employers", attributeNodes = {@NamedAttributeNode("customers")}),
                @NamedSubgraph(name = "roles", attributeNodes = {@NamedAttributeNode("customers")})
        }
)
public class Customer extends AbstractEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "age")
    private Integer age;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    private Set<Account> accounts;


    @ManyToMany
    @JoinTable(
            name = "customers_employers",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employer_id", referencedColumnName = "id")
    )
    private List<Employer> employers;

    @ManyToMany
    @JoinTable(
            name = "customers_roles",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @JsonManagedReference
    private Set<Role> roles;

    public Customer(String name, String surname, String email, Integer age, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
        this.password = password;
        this.accounts = new HashSet<>();
        this.roles = roles;
    }

}
