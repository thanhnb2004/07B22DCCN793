package com.supermarket.qlst.service;

import com.supermarket.qlst.model.ImportInvoice;
import com.supermarket.qlst.model.ImportItem;
import com.supermarket.qlst.model.Product;
import com.supermarket.qlst.model.Supplier;
import com.supermarket.qlst.model.WarehouseStaff;

import java.util.ArrayList;
import java.util.List;

public class ImportService {
    private final List<ImportInvoice> importInvoices = new ArrayList<>();
    private final ProductCatalog productCatalog = new ProductCatalog();

    public ImportInvoice createInvoice(Supplier supplier, WarehouseStaff warehouseStaff) {
        ImportInvoice invoice = new ImportInvoice(warehouseStaff, supplier);
        return invoice;
    }

    public void addImportItem(ImportInvoice invoice, Product product, int quantity, double unitPrice, String note) {
        ImportItem item = new ImportItem(product, quantity, unitPrice, note);
        invoice.addItem(item);
    }

    public void saveImportInvoice(ImportInvoice invoice) {
        importInvoices.add(invoice);
        for (ImportItem item : invoice.getItems()) {
            productCatalog.increaseStockQuantity(item.getProduct(), item.getQuantity());
        }
    }

    public Product ensureProductExists(String productCode, String name, String productType, String brand,
                                       String unit, double sellingPrice, int stock, String description) {
        Product product = productCatalog.findByCode(productCode);
        if (product == null) {
            product = new Product(productCode, name, productType, brand, unit, sellingPrice, stock, description);
            productCatalog.save(product);
        } else {
            if (name != null && !name.isBlank()) {
                product.setName(name);
            }
            if (productType != null && !productType.isBlank()) {
                product.setProductType(productType);
            }
            if (brand != null && !brand.isBlank()) {
                product.setBrand(brand);
            }
            if (unit != null && !unit.isBlank()) {
                product.setUnit(unit);
            }
            if (sellingPrice > 0) {
                product.setSellingPrice(sellingPrice);
            }
            if (description != null) {
                product.setDescription(description);
            }
            productCatalog.save(product);
        }
        return product;
    }

    public List<ImportInvoice> getImportInvoices() {
        return new ArrayList<>(importInvoices);
    }
}
