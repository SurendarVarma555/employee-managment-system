package com.ems.app.service;

import com.ems.app.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee (Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);


}
