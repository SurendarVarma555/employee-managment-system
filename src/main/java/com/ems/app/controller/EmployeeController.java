package com.ems.app.controller;

import com.ems.app.entity.Employee;
import com.ems.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ems")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /* Api for CREATE EMPLOYEE */

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveEmployee (@RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }


}
