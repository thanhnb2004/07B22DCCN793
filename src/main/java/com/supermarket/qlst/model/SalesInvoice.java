package com.supermarket.qlst.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class SalesInvoice {
    private final String salesInvoiceCode;
    private SaleStaff saleStaff;
    private Customer customer;
    private final List<SalesItem> items = new ArrayList<>();
    private LocalDate saleDate = LocalDate.now();
    private String note;

    public SalesInvoice() {
        this.salesInvoiceCode = UUID.randomUUID().toString();
    }

    public String getSalesInvoiceCode() {
        return salesInvoiceCode;
    }

    public SaleStaff getSaleStaff() {
        return saleStaff;
    }

    public void setSaleStaff(SaleStaff saleStaff) {
        this.saleStaff = saleStaff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SalesItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(SalesItem item) {
        items.add(item);
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotalAmount() {
        return items.stream().mapToDouble(item -> item.getUnitPrice() * item.getQuantity()).sum();
    }
}
