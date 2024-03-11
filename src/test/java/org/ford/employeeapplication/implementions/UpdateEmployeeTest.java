package org.ford.employeeapplication.implementions;

import org.ford.employeeapplication.services.EmployeeService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class UpdateEmployeeTest {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "put your password";
    @Test
    public void testUpdateEmployeeWithPositiveCase() throws ClassNotFoundException {
        EmployeeService employeeService = new EmployeeImpl();
        int id = 1;
        String updatedName = "Jackson";
        String updatedSalary = "60000";
        String updatedDepartment = "Finance";

        employeeService.updateEmployee(id, updatedName, updatedSalary, updatedDepartment);
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                assertEquals(updatedName, resultSet.getString("name"));
                assertEquals(updatedSalary, resultSet.getString("salary"));
                assertEquals(updatedDepartment, resultSet.getString("department"));
            } else {
                fail("Employee with the given ID not found in the database");
            }
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateEmployeeWithInvalidId() throws ClassNotFoundException {
        EmployeeService employeeService = new EmployeeImpl();
        int invalidId = -1;

        employeeService.updateEmployee(invalidId, "Jackson", "50000", "IT");

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, invalidId);
            ResultSet resultSet = preparedStatement.executeQuery();
            assertFalse(resultSet.next(), "No employee should be updated with an invalid ID");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}