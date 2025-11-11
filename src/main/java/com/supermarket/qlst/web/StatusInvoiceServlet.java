package com.supermarket.qlst.web;

import com.supermarket.qlst.model.ImportInvoice;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StatusInvoiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ImportInvoice invoice = (ImportInvoice) req.getSession().getAttribute("lastImportInvoice");
        req.setAttribute("importInvoice", invoice);
        req.getRequestDispatcher("/WEB-INF/jsp/StatusInvoice.jsp").forward(req, resp);
    }
}
