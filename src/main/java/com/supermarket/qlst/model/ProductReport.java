package com.supermarket.qlst.model;

import java.time.LocalDate;

public class ProductReport extends Product {
    private int soldQuantity;
    private double totalImportValue;
    private int remainingQuantity;
    private LocalDate startDate;
    private LocalDate endDate;

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public double getTotalImportValue() {
        return totalImportValue;
    }

    public void setTotalImportValue(double totalImportValue) {
        this.totalImportValue = totalImportValue;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
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
