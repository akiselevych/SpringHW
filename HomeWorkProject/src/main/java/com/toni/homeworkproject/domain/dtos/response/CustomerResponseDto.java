package com.toni.homeworkproject.domain.dtos.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.toni.homeworkproject.domain.Account;
import com.toni.homeworkproject.domain.Employer;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employers")
public class CustomerResponseDto {
    @JsonView({CustomerResponseDtoView.Single.class, CustomerResponseDtoView.Many.class})
    private Long id;
    @JsonView({CustomerResponseDtoView.Single.class, CustomerResponseDtoView.Many.class})
    private String name;
    @JsonView({CustomerResponseDtoView.Single.class, CustomerResponseDtoView.Many.class})
    private String surname;
    @JsonView(CustomerResponseDtoView.Single.class)
    private String email;
    @JsonView(CustomerResponseDtoView.Single.class)
    private Integer age;
    @JsonView(CustomerResponseDtoView.Single.class)
    private String phone;

    @JsonView(CustomerResponseDtoView.Single.class)
    private Date createdDate;
    @JsonView(CustomerResponseDtoView.Single.class)
    private Date lastModifiedDate;

    @JsonIgnoreProperties("customer")
    @JsonView({CustomerResponseDtoView.Single.class, CustomerResponseDtoView.Many.class})
    private Set<AccountResponseDto> accounts;


    @JsonView({CustomerResponseDtoView.Single.class})
    @JsonIgnoreProperties("customers")
    private List<Employer> employers;

}
