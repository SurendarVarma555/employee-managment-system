package com.ems.app.service.impl;

import com.ems.app.entity.Employee;
import com.ems.app.exception.ResourceNotFoundException;
import com.ems.app.repository.EmployeeRepository;
import com.ems.app.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee (Employee employee){
        log.info("Saving employee...");
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees (){
        log.info("Find All employees");
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id){
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()){
//            return employee.get();
//        }else {
//            throw new ResourceNotFoundException("Employee","id",id);
//        }
        log.info("Fetching Employee by id ");
        return  employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee","id",id));
    }

    @Override
    public Employee updateEmployee (Employee employee, Integer id){

        // first we need to check whether employee with given id is exist or not

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                                                            ()-> new ResourceNotFoundException("Employee","id",id));

        // We have to set/update the existing employee by setting the values which came from client end i.e.,, Employee employee
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());


        log.info("Saving updated details in at server side");
        employeeRepository.save(existingEmployee);
        return existingEmployee;
    }

    @Override
    public void removeEmployee (Integer id){
        // check whether the given employee id is exist or not or else throw Exception
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        log.info("Deleting Resource from server");
        employeeRepository.deleteById(id);

    }

}
