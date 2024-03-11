<%@ page import="org.ford.employeeapplication.model.Employee" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css">
        <title>Update Employee</title>
    </head>
    <body>
        <% Employee employee = (Employee) request.getAttribute("employee");
        %>
        <h3 class="update-heading">Update Employee</h3>
        <div class="update-form">
            <form action="update-employee" method="post">
                <label>Employee Name</label>
                <input type="hidden" name="id" value="<%= employee.getId() %>" >
                <input type="text" name="name" value="<%= employee.getName() %>" required><br>
                <label>Employee Salary</label>
                <input type="text" name="salary" value="<%= employee.getSalary() %>" required><br>
                <label>Employee Department</label>
                <input type="text" name="department" value="<%= employee.getDepartment() %>" required><br>
                <input type="submit" id="updateButton">
            </form>
        </div>
    </body>
</html>
