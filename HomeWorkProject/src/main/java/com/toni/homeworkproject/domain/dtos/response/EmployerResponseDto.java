package com.toni.homeworkproject.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.toni.homeworkproject.domain.Customer;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customers")
public class EmployerResponseDto {
    private Long id;
    private String name;
    private String address;

    @JsonIgnoreProperties({"employers", "accounts"})
    private Set<CustomerResponseDto> customers;
}
