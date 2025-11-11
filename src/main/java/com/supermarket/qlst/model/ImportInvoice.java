package com.supermarket.qlst.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ImportInvoice {
    private final String importInvoiceCode;
    private LocalDate importDate;
    private WarehouseStaff warehouseStaff;
    private Supplier supplier;
    private final List<ImportItem> items = new ArrayList<>();
    private String note;

    public ImportInvoice() {
        this.importInvoiceCode = UUID.randomUUID().toString();
        this.importDate = LocalDate.now();
    }

    public ImportInvoice(WarehouseStaff warehouseStaff, Supplier supplier) {
        this();
        this.warehouseStaff = warehouseStaff;
        this.supplier = supplier;
    }

    public String getImportInvoiceCode() {
        return importInvoiceCode;
    }

    public LocalDate getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDate importDate) {
        this.importDate = importDate;
    }

    public WarehouseStaff getWarehouseStaff() {
        return warehouseStaff;
    }

    public void setWarehouseStaff(WarehouseStaff warehouseStaff) {
        this.warehouseStaff = warehouseStaff;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<ImportItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(ImportItem item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(ImportItem::getTotalAmount).sum();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
