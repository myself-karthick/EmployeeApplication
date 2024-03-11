package org.ford.employeeapplication.implementions;

import org.ford.employeeapplication.model.Employee;
import org.ford.employeeapplication.services.EmployeeService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeImpl implements EmployeeService
{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Put your password";

    public void addEmployee(String name, String salary, String department)
    {
        try
        {
            checkInput(name, salary, department);
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String insertQuery = "INSERT INTO employee (name, salary, department) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, salary);
            preparedStatement.setString(3, department);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private static void checkInput(String name, String salary, String department) {
        if (salary.startsWith("-")) {
            throw new IllegalArgumentException("Salary can't be negative");
        }
        if (salary.isEmpty() || name.isEmpty() || department.isEmpty()) {
            throw new IllegalArgumentException("Input can't be empty values");
        }
    }


    public void updateEmployee(int id, String name, String salary, String department)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String updateQuery = "UPDATE employee SET name = ?, salary = ?, department = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, salary);
            preparedStatement.setString(3, department);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated");

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteEmployee(int id)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String deleteQuery = "DELETE FROM employee WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) deleted");

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }


    public Employee getEmployee(int id)
    {
        Employee employee = new Employee();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String selectQuery = "SELECT * FROM employee WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee.setId(id);
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getString("salary"));
                employee.setDepartment(resultSet.getString("department"));
            }
            preparedStatement.close();
            connection.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return employee;
    }

    public List<Employee> getEmployees()
    {
        List<Employee> employees = new ArrayList<>();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String selectQuery = "SELECT * FROM employee";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getString("salary"));
                employee.setDepartment(resultSet.getString("department"));
                employees.add(employee);

            }
            preparedStatement.close();
            connection.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
        return employees;
    }

}
