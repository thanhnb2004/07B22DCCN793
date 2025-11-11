package com.supermarket.qlst.model;

import java.util.Objects;

public class Product {
    private String productCode;
    private String name;
    private String productType;
    private String brand;
    private String unit;
    private double sellingPrice;
    private int stockQuantity;
    private String description;

    public Product() {
    }

    public Product(String productCode, String name, String productType, String brand, String unit,
                   double sellingPrice, int stockQuantity, String description) {
        this.productCode = productCode;
        this.name = name;
        this.productType = productType;
        this.brand = brand;
        this.unit = unit;
        this.sellingPrice = sellingPrice;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void increaseStockQuantity(int amount) {
        if (amount > 0) {
            this.stockQuantity += amount;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(productCode, product.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode);
    }
}
