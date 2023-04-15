package com.ems.app.repository;

import com.ems.app.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {
}
