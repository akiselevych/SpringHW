package com.toni.homeworkproject.dao;

import com.toni.homeworkproject.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
