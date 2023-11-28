package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerJpaRepository extends JpaRepository<Employer,Long> {
}
