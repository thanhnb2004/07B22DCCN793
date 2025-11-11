package com.supermarket.qlst.model;

import java.util.Objects;

public class Supplier {
    private String supplierCode;
    private String legalName;
    private String taxCode;
    private String address;
    private String phone;
    private String email;
    private String note;

    public Supplier() {
    }

    public Supplier(String supplierCode, String legalName, String taxCode, String address, String phone, String email, String note) {
        this.supplierCode = supplierCode;
        this.legalName = legalName;
        this.taxCode = taxCode;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier supplier)) return false;
        return Objects.equals(supplierCode, supplier.supplierCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierCode);
    }
}
