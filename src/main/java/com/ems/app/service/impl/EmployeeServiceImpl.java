package com.ems.app.service.impl;

import com.ems.app.entity.Employee;
import com.ems.app.repository.EmployeeRepository;
import com.ems.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee (Employee employee){

        return employeeRepository.save(employee);
    }
}
