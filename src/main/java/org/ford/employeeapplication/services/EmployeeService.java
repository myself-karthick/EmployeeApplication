package org.ford.employeeapplication.services;

import org.ford.employeeapplication.model.Employee;

import java.util.List;

public interface EmployeeService
{
    void addEmployee(String name, String salary, String department);
    void updateEmployee(int id, String name, String salary, String department);
    void deleteEmployee(int id);
    Employee getEmployee(int id);
    List<Employee> getEmployees();
}
