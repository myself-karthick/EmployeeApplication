package org.ford.employeeapplication.controller;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.ford.employeeapplication.implementions.EmployeeImpl;
import org.ford.employeeapplication.services.EmployeeService;

@WebServlet(name = "UpdateEmployee", value = "/update-employee")
public class UpdateEmployee extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String salary = request.getParameter("salary");
        String department = request.getParameter("department");
        int id = Integer.parseInt(request.getParameter("id"));

        EmployeeService employeeService = new EmployeeImpl();
        employeeService.updateEmployee(id,name,salary,department);

        response.sendRedirect("view-employees");

    }
}