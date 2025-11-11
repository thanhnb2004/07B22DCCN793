package com.supermarket.qlst.web;

import com.supermarket.qlst.model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StatusRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("registeredCustomer");
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/jsp/StatusRegister.jsp").forward(req, resp);
    }
}
