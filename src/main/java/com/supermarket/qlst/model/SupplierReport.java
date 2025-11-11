package com.supermarket.qlst.model;

import java.time.LocalDate;

public class SupplierReport extends Supplier {
    private int supplyCount;
    private int suppliedQuantity;
    private String suppliedCategories;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getSupplyCount() {
        return supplyCount;
    }

    public void setSupplyCount(int supplyCount) {
        this.supplyCount = supplyCount;
    }

    public int getSuppliedQuantity() {
        return suppliedQuantity;
    }

    public void setSuppliedQuantity(int suppliedQuantity) {
        this.suppliedQuantity = suppliedQuantity;
    }

    public String getSuppliedCategories() {
        return suppliedCategories;
    }

    public void setSuppliedCategories(String suppliedCategories) {
        this.suppliedCategories = suppliedCategories;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
