package com.ems.app.controller;

import com.ems.app.entity.Employee;
import com.ems.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ems")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /* Api for CREATE EMPLOYEE
    * URL:http://localhost:8085/api/ems/saveEmployee
    * */

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveEmployee (@RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    /* Api for GET ALL EMPLOYEEs REST API
    *
    * URL:http://localhost:8085/api/ems/getAllEmployees
    *  */

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }




}
