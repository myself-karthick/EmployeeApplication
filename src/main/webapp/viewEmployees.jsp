<%@ page import="org.ford.employeeapplication.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Employee</title>
        <link rel="stylesheet" href="viewstyle.css">
    </head>
    <body>
        <h3>Employee details</h3>
        <table class="employee-table">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>Department</th>
                <th>Actions</th>
            </tr>
            <% List<Employee> employees = (List<Employee>) request.getAttribute("employees");
                for (Employee employee : employees) { %>
            <tr>
                <td><%= employee.getId() %></td>
                <td><%= employee.getName() %></td>
                <td><%= employee.getSalary() %></td>
                <td><%= employee.getDepartment() %></td>
                <td class="action-column">
                    <form action="get-employee" class="edit-form">
                        <input type="hidden" name="id" value="<%= employee.getId() %>">
                        <input type="submit" value="Edit" class="edit-button">
                    </form>
                    <form action="delete-employee" method="post" class="delete-form">
                        <input type="hidden" name="id" value="<%= employee.getId() %>">
                        <input type="submit" value="Delete" class="delete-button">
                    </form>
                </td>
            </tr>
            <% } %>
        </table>

    </body>
</html>
