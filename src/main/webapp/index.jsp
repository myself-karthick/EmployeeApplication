<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <h3 class="add-heading">Add Employee</h3>
        <div class="add-form">
            <form action="add-employee" method="post">
                <label>Employee Name</label>
                <input type="text" name="name" required><br>
                <label>Employee Salary</label>
                <input type="text" name="salary" required><br>
                <label>Employee Department</label>
                <input type="text" name="department" required><br>
                <input type="submit" id="addButton">
            </form>
        </div>
    </body>
</html>