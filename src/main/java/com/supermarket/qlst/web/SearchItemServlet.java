package com.supermarket.qlst.web;

import com.supermarket.qlst.model.Product;
import com.supermarket.qlst.service.ProductCatalog;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class SearchItemServlet extends HttpServlet {
    private final ProductCatalog productCatalog = new ProductCatalog();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        String supplierId = req.getParameter("supplierId");
        List<Product> products = productCatalog.searchItems(keyword);
        req.setAttribute("products", products);
        req.setAttribute("keyword", keyword);
        req.setAttribute("supplierId", supplierId);
        req.getRequestDispatcher("/WEB-INF/jsp/SearchItemView.jsp").forward(req, resp);
    }
}
