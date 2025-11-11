package com.supermarket.qlst.model;

import java.time.LocalDate;

public class RevenueReport {
    private double totalRevenue;
    private double averageRevenue;
    private LocalDate startDate;
    private LocalDate endDate;

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getAverageRevenue() {
        return averageRevenue;
    }

    public void setAverageRevenue(double averageRevenue) {
        this.averageRevenue = averageRevenue;
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
