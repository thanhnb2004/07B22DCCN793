package com.supermarket.qlst.model;

import java.time.LocalDate;

public class DeliveryStaff {
    private String fullName;
    private LocalDate birthOfDate;
    private String email;
    private String address;
    private String phone;

    public DeliveryStaff() {
    }

    public DeliveryStaff(String fullName, LocalDate birthOfDate, String email, String address, String phone) {
        this.fullName = fullName;
        this.birthOfDate = birthOfDate;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(LocalDate birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
