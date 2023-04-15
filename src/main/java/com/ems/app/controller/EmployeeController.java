package com.ems.app.controller;

import com.ems.app.entity.Employee;
import com.ems.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ems")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /* Api for CREATE EMPLOYEE
    * URL:http://localhost:8085/api/ems/saveEmployee
    * */

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee (@RequestBody Employee employee){

        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    /* Api for GET ALL EMPLOYEEs REST API
    *
    * URL:http://localhost:8085/api/ems/getAllEmployees
    *  */

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }


    /* Api for CREATE EMPLOYEE
     * URL:http://localhost:8085/api/ems/saveEmployee
     * */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id){

        return new ResponseEntity<Employee>(employeeService.getEmployeeById(id),HttpStatus.OK);

    }

    /* Api for Update EMPLOYEE
     * URL:http://localhost:8085/api/ems/saveEmployee
     * */
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") Integer id){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable("id") Integer id){
        employeeService.removeEmployee(id);
        return new ResponseEntity<>("Employee record deleted successfully with given Id: "+id,HttpStatus.OK);

    }



}
