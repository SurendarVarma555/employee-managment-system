package com.ems.app.serviceimpltest;

import com.ems.app.entity.Employee;
import com.ems.app.exception.ResourceNotFoundException;
import com.ems.app.repository.EmployeeRepository;
import com.ems.app.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals(employee.getId(), savedEmployee.getId());
        assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employee.getLastName(), savedEmployee.getLastName());
        assertEquals(employee.getEmail(), savedEmployee.getEmail());

        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "John", "Doe", "john.doe@example.com"));
        employees.add(new Employee(2, "Jane", "Doe", "jane.doe@example.com"));
        employees.add(new Employee(3, "Bob", "Smith", "bob.smith@example.com"));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> returnedEmployees = employeeService.getAllEmployees();

        assertNotNull(returnedEmployees);
        assertEquals(3, returnedEmployees.size());
        assertEquals(employees.get(0), returnedEmployees.get(0));
        assertEquals(employees.get(1), returnedEmployees.get(1));
        assertEquals(employees.get(2), returnedEmployees.get(2));

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee returnedEmployee = employeeService.getEmployeeById(id);

        assertNotNull(returnedEmployee);
        assertEquals(employee, returnedEmployee);

        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    void testGetEmployeeByIdWithNonExistingId() {
        Integer id = 1;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(id));

        verify(employeeRepository, times(1)).findById(id);
    }
    @DisplayName("testUpdateEmployeeWhenEmployeeExists")
    @Test
    public void testUpdateEmployeeWhenEmployeeExists() {
        Employee existingEmployee = new Employee();
        existingEmployee.setId(1);
        existingEmployee.setFirstName("John");
        existingEmployee.setLastName("Doe");
        existingEmployee.setEmail("john.doe@example.com");

        when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(existingEmployee));

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1);
        updatedEmployee.setFirstName("Jane");
        updatedEmployee.setLastName("Doe");
        updatedEmployee.setEmail("jane.doe@example.com");

        Employee result = employeeService.updateEmployee(updatedEmployee, 1);

        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, times(1)).save(existingEmployee);

        assertEquals(result.getId(), 1);
        assertEquals(result.getFirstName(), "Jane");
        assertEquals(result.getLastName(), "Doe");
        assertEquals(result.getEmail(), "jane.doe@example.com");
    }

    @Test
    public void testUpdateEmployeeWhenEmployeeDoesNotExist() {
        when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1);
        updatedEmployee.setFirstName("Jane");
        updatedEmployee.setLastName("Doe");
        updatedEmployee.setEmail("jane.doe@example.com");

        assertThrows(ResourceNotFoundException.class,
                () -> employeeService.updateEmployee(updatedEmployee, 1));

        verify(employeeRepository, times(1)).findById(1);
        verify(employeeRepository, never()).save(any());
    }

    @Test
    public void testRemoveEmployee() {
        // given
        int id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // when
        employeeService.removeEmployee(id);

        // then
        verify(employeeRepository, times(1)).deleteById(id);
    }

    @Test
    public void testRemoveEmployeeNotFoundException() {
        // given
        int id = 1;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(ResourceNotFoundException.class, () -> employeeService.removeEmployee(id));
    }



    }

