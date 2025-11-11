package com.supermarket.qlst.web;

import com.supermarket.qlst.model.ImportInvoice;
import com.supermarket.qlst.model.Supplier;
import com.supermarket.qlst.model.WarehouseStaff;
import com.supermarket.qlst.service.DataStore;
import com.supermarket.qlst.service.ImportService;
import com.supermarket.qlst.service.SupplierDirectory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ImportInvoiceServlet extends HttpServlet {
    private final SupplierDirectory supplierDirectory = new SupplierDirectory();
    private final ImportService importService = new ImportService();
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String supplierId = req.getParameter("supplierId");
        if (supplierId == null || supplierId.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/suppliers");
            return;
        }
        Supplier supplier = supplierDirectory.findSupplier(supplierId);
        if (supplier == null) {
            req.setAttribute("supplier", null);
            req.getRequestDispatcher("/WEB-INF/jsp/ImportFormView.jsp").forward(req, resp);
            return;
        }
        ImportInvoice invoice = getCurrentInvoice(req, supplier);
        req.setAttribute("supplier", supplier);
        req.setAttribute("invoiceItems", invoice.getItems());
        req.setAttribute("invoiceNote", invoice.getNote());
        req.getRequestDispatcher("/WEB-INF/jsp/ImportFormView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("save".equals(action)) {
            String supplierId = req.getParameter("supplierId");
            Supplier supplier = supplierDirectory.findSupplier(supplierId);
            ImportInvoice invoice = getCurrentInvoice(req, supplier);
            invoice.setNote(req.getParameter("note"));
            if (invoice.isEmpty()) {
                req.setAttribute("supplier", supplier);
                req.setAttribute("invoiceItems", invoice.getItems());
                req.setAttribute("invoiceNote", invoice.getNote());
                req.setAttribute("error", "Hóa đơn phải có ít nhất một mặt hàng");
                req.getRequestDispatcher("/WEB-INF/jsp/ImportFormView.jsp").forward(req, resp);
                return;
            }
            importService.saveImportInvoice(invoice);
            req.getSession().setAttribute("lastImportInvoice", invoice);
            req.getSession().removeAttribute("currentImportInvoice");
            resp.sendRedirect(req.getContextPath() + "/import/status");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/suppliers");
    }

    private ImportInvoice getCurrentInvoice(HttpServletRequest req, Supplier supplier) {
        ImportInvoice invoice = (ImportInvoice) req.getSession().getAttribute("currentImportInvoice");
        if (invoice == null || (supplier != null && !supplier.equals(invoice.getSupplier()))) {
            WarehouseStaff staff = dataStore.getDefaultWarehouseStaff();
            invoice = importService.createInvoice(supplier, staff);
            req.getSession().setAttribute("currentImportInvoice", invoice);
        }
        return invoice;
    }
}
