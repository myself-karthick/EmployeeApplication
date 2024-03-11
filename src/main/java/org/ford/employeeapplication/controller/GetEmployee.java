package org.ford.employeeapplication.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.ford.employeeapplication.implementions.EmployeeImpl;
import org.ford.employeeapplication.model.Employee;
import org.ford.employeeapplication.services.EmployeeService;

import java.io.IOException;

@WebServlet(name = "getEmployee", value = "/get-employee")
public class GetEmployee extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));

        EmployeeService employeeService = new EmployeeImpl();
        Employee employee = employeeService.getEmployee(id);

        request.setAttribute("id",id);
        request.setAttribute("employee",employee);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("update.jsp");
        requestDispatcher.forward(request,response);

    }
}
