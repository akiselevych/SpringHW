package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

}
