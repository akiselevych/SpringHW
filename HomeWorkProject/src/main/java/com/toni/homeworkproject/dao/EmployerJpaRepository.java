package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Customer;
import com.toni.homeworkproject.domain.Employer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerJpaRepository extends JpaRepository<Employer,Long> {
    @EntityGraph("employersWithCustomersAndAccounts")
    List<Employer> findAll(Sort sort);

    @EntityGraph("employersWithCustomersAndAccounts")
    Optional<Employer> findById(Long id);
}
