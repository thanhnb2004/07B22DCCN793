package com.supermarket.qlst.web;

import com.supermarket.qlst.model.Customer;
import com.supermarket.qlst.model.Gender;
import com.supermarket.qlst.service.MemberRegistry;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RegisterMemberServlet extends HttpServlet {
    private final MemberRegistry memberRegistry = new MemberRegistry();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/RegisterMemberFormView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String genderParam = req.getParameter("gender");
        String birthParam = req.getParameter("birthOfDate");

        Gender gender = null;
        if (genderParam != null && !genderParam.isBlank()) {
            try {
                gender = Gender.valueOf(genderParam);
            } catch (IllegalArgumentException ignored) {
                // giữ null để validate hiển thị lỗi
            }
        }

        LocalDate birthDate = null;
        if (birthParam != null && !birthParam.isBlank()) {
            try {
                birthDate = LocalDate.parse(birthParam);
            } catch (DateTimeParseException e) {
                // keep null
            }
        }

        List<String> errors = memberRegistry.validateRegistration(email, password, confirmPassword, firstName, lastName,
                address, birthDate, gender, phone);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/jsp/RegisterMemberFormView.jsp").forward(req, resp);
            return;
        }

        try {
            Customer customer = memberRegistry.registerMembership(email, password, firstName, lastName, address, birthDate, gender, phone);
            req.getSession().setAttribute("registeredCustomer", customer);
            resp.sendRedirect(req.getContextPath() + "/register/status");
        } catch (IllegalArgumentException ex) {
            errors.add(ex.getMessage());
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/jsp/RegisterMemberFormView.jsp").forward(req, resp);
        }
    }
}
