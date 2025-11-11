package com.supermarket.qlst.web;

import com.supermarket.qlst.model.ImportInvoice;
import com.supermarket.qlst.model.Product;
import com.supermarket.qlst.model.Supplier;
import com.supermarket.qlst.service.DataStore;
import com.supermarket.qlst.service.ImportService;
import com.supermarket.qlst.service.SupplierDirectory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddImportItemServlet extends HttpServlet {
    private final SupplierDirectory supplierDirectory = new SupplierDirectory();
    private final ImportService importService = new ImportService();
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String supplierId = req.getParameter("supplierId");
        Supplier supplier = supplierDirectory.findSupplier(supplierId);
        ImportInvoice invoice = (ImportInvoice) req.getSession().getAttribute("currentImportInvoice");
        if (invoice == null || (supplier != null && !supplier.equals(invoice.getSupplier()))) {
            invoice = importService.createInvoice(supplier, dataStore.getDefaultWarehouseStaff());
            req.getSession().setAttribute("currentImportInvoice", invoice);
        }

        String productCode = req.getParameter("productCode");
        String productName = req.getParameter("productName");
        String productType = req.getParameter("productType");
        String brand = req.getParameter("brand");
        String unit = req.getParameter("unit");
        String sellingPriceParam = req.getParameter("sellingPrice");
        String stockParam = req.getParameter("stock");
        String description = req.getParameter("description");
        int quantity = parseInt(req.getParameter("quantity"), 1);
        double unitPrice = parseDouble(req.getParameter("unitPrice"), 0);
        double sellingPrice = parseDouble(sellingPriceParam, 0);
        int stock = parseInt(stockParam, 0);
        String note = req.getParameter("note");

        Product product = importService.ensureProductExists(productCode, productName, productType, brand, unit, sellingPrice, stock, description);
        importService.addImportItem(invoice, product, quantity, unitPrice, note);
        req.getSession().setAttribute("currentImportInvoice", invoice);
        resp.sendRedirect(req.getContextPath() + "/import?supplierId=" + supplierId);
    }

    private int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private double parseDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
