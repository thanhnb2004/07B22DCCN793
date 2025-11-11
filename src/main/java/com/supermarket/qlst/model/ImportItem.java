package com.supermarket.qlst.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImportItem {
    private Product product;
    private int quantity;
    private double unitPrice;
    private String note;

    public ImportItem() {
    }

    public ImportItem(Product product, int quantity, double unitPrice, String note) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.note = note;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotalAmount() {
        BigDecimal qty = BigDecimal.valueOf(quantity);
        BigDecimal price = BigDecimal.valueOf(unitPrice);
        return qty.multiply(price).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
