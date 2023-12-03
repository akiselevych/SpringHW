package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    @EntityGraph("customerWithAccountsAndEmployers")
    List<Customer> findAll(Sort sort);
    @EntityGraph("customerWithAccountsAndEmployers")
    List<Customer> findAll();
    @EntityGraph("customerWithAccountsAndEmployers")
    Page<Customer> findAll(Pageable pageable);

    @EntityGraph("customerWithAccountsAndEmployers")
    Optional<Customer> findById(Long id);

}
