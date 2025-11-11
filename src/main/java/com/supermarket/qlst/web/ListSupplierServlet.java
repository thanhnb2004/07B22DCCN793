package com.supermarket.qlst.web;

import com.supermarket.qlst.model.Supplier;
import com.supermarket.qlst.service.SupplierDirectory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ListSupplierServlet extends HttpServlet {
    private final SupplierDirectory supplierDirectory = new SupplierDirectory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Supplier> suppliers = supplierDirectory.searchSuppliers(keyword);
        req.setAttribute("suppliers", suppliers);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/WEB-INF/jsp/ListSupplierView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            Supplier supplier = new Supplier();
            supplier.setSupplierCode(req.getParameter("supplierCode"));
            supplier.setLegalName(req.getParameter("legalName"));
            supplier.setTaxCode(req.getParameter("taxCode"));
            supplier.setAddress(req.getParameter("address"));
            supplier.setPhone(req.getParameter("phone"));
            supplier.setEmail(req.getParameter("email"));
            supplier.setNote(req.getParameter("note"));
            supplierDirectory.saveSupplier(supplier);
        }
        resp.sendRedirect(req.getContextPath() + "/suppliers");
    }
}
