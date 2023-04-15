package com.ems.app.service;

import com.ems.app.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee (Employee employee);

    List<Employee> getAllEmployees();
}
