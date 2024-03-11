package org.ford.employeeapplication.implementions;

import org.ford.employeeapplication.model.Employee;
import org.ford.employeeapplication.services.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetEmployeeTest {

    @Test
    public void testGetEmployee_PositiveCase() {
        EmployeeService employeeService = new EmployeeImpl();
        int id = 1;

        Employee retrievedEmployee = employeeService.getEmployee(id);

        assertEquals(id, retrievedEmployee.getId());
        assertEquals("John Doe", retrievedEmployee.getName());
        assertEquals("50000", retrievedEmployee.getSalary());
        assertEquals("IT", retrievedEmployee.getDepartment());
    }
    @Test
    public void testGetEmployee_NegativeCase() {
        EmployeeService employeeService = new EmployeeImpl();
        int id = -1;

        Employee retrievedEmployee = employeeService.getEmployee(id);

        assertEquals(0, retrievedEmployee.getId());
        assertNull(retrievedEmployee.getName());
        assertNull(retrievedEmployee.getSalary());
        assertNull(retrievedEmployee.getDepartment());
    }
}