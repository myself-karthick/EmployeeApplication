package org.ford.employeeapplication.implementions;

import org.ford.employeeapplication.model.Employee;
import org.ford.employeeapplication.services.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetEmployeesTest {

    @Test
    public void testGetEmployeesWithPositiveCase() {
        EmployeeService employeeService = new EmployeeImpl();

        List<Employee> employees = employeeService.getEmployees();

        assertNotNull(employees);
        assertFalse(employees.isEmpty(), "List of employees should not be empty");
    }

    @Test
    public void testGetEmployeesWithEmptyDatabase() {
        EmployeeService employeeService = new EmployeeImpl();
        List<Employee> employees = employeeService.getEmployees();
        assertNotNull(employees);
        assertTrue(employees.isEmpty(), "List of employees should be empty for an empty database");
    }
}