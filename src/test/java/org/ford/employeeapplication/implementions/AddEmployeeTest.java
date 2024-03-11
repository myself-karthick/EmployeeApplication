package org.ford.employeeapplication.implementions;

import org.ford.employeeapplication.services.EmployeeService;
import org.junit.jupiter.api.Test;

import static org.ford.employeeapplication.implementions.EmployeeImpl.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddEmployeeTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "put your password";
    @Test
    public void testAddEmployee() throws ClassNotFoundException {
        EmployeeService employeeService = new EmployeeImpl();
        String name = "John";
        String salary = "50000";
        String department = "IT";

        employeeService.addEmployee(name, salary, department);
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM employee WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals(name, resultSet.getString("name"));
                assertEquals(salary, resultSet.getString("salary"));
                assertEquals(department, resultSet.getString("department"));
            } else {
                fail("Employee not found in the database");
            }
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testAddEmployeeWithNegativeSalary() {
        EmployeeService employeeService = new EmployeeImpl();
        String name = "Tom";
        String salary = "-50000";
        String department = "IT";
        assertThrows(IllegalArgumentException.class, ()->employeeService.addEmployee(name, salary, department));

    }

    @Test
    public void testAddEmployee_EmptyOrNullValues() {
        EmployeeService employeeService = new EmployeeImpl();
        String name = "Tom";
        String salary = "";
        String department = "IT";
        assertThrows(IllegalArgumentException.class, ()->employeeService.addEmployee(name, salary, department));
    }
}
