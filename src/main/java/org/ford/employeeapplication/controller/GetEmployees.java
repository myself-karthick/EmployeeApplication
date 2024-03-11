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
import java.util.List;

@WebServlet(name = "getEmployees", value = "/view-employees")
public class GetEmployees extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        EmployeeService employeeService = new EmployeeImpl();
        List<Employee> employeeList = employeeService.getEmployees();
        request.setAttribute("employees",employeeList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewEmployees.jsp");
        requestDispatcher.forward(request,response);
    }

}
