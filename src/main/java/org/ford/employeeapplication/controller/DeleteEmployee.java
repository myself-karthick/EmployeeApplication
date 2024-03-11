package org.ford.employeeapplication.controller;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.ford.employeeapplication.implementions.EmployeeImpl;
import org.ford.employeeapplication.services.EmployeeService;

@WebServlet(name = "DeleteEmployee", value = "/delete-employee")
public class DeleteEmployee extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeService employeeService = new EmployeeImpl();
        employeeService.deleteEmployee(id);

        response.sendRedirect("view-employees");
    }
}