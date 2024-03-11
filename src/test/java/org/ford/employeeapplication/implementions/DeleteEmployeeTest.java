package org.ford.employeeapplication.implementions;

import org.ford.employeeapplication.services.EmployeeService;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.*;

class DeleteEmployeeTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "put your password";
    @Test
    public void testDeleteEmployeeWithPositiveCase() throws ClassNotFoundException {
        EmployeeService employeeService = new EmployeeImpl();
        int id = 1;

        employeeService.deleteEmployee(id);
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "DELETE FROM employee WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            assertFalse(resultSet.next(), "Employee with the given ID should be deleted from the database");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteEmployee_InvalidId() throws ClassNotFoundException {

        EmployeeService employeeService = new EmployeeImpl();
        int invalidId = -1;

        employeeService.deleteEmployee(invalidId);
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, invalidId);
            ResultSet resultSet = preparedStatement.executeQuery();
            assertFalse(resultSet.next(), "No employee should be deleted with an invalid ID");
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}