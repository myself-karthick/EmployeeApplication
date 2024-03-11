package org.ford.employeeapplication.controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.ford.employeeapplication.implementions.EmployeeImpl;
import org.ford.employeeapplication.services.EmployeeService;

@WebServlet(name = "addEmployee", value = "/add-employee")
public class AddEmployee extends HttpServlet
{ 
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String salary = request.getParameter("salary");
        String department = request.getParameter("department");

        EmployeeService employeeService = new EmployeeImpl();
        employeeService.addEmployee(name,salary,department);

        response.sendRedirect("view-employees");

    }

}
