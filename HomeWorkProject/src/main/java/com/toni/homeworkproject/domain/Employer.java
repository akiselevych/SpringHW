package com.toni.homeworkproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employers")
@Getter
@Setter
@NamedEntityGraph(name = "employersWithCustomersAndAccounts",
        attributeNodes = {@NamedAttributeNode(value = "customers",subgraph = "customers")},
        subgraphs = {
                @NamedSubgraph(
                        name = "customers",
                        attributeNodes = {
                                @NamedAttributeNode(value = "accounts"),
                                @NamedAttributeNode(value = "employers")
                        }
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customers")
@EqualsAndHashCode(of = "id", callSuper = true)
public class Employer extends AbstractEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;


    @ManyToMany(mappedBy = "employers", cascade = {CascadeType.ALL})
    private Set<Customer> customers;
}
