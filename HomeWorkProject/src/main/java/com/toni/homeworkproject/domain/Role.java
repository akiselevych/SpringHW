package com.toni.homeworkproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString(exclude = "customers")
@NoArgsConstructor
@EqualsAndHashCode(of = "id", callSuper = true)
public class Role extends AbstractEntity implements GrantedAuthority {


    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles",cascade = {CascadeType.ALL})
    @JsonBackReference
    private Set<Customer> customers;


    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
