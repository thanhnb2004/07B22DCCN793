package com.supermarket.qlst.model;

import java.time.LocalDate;

public class Customer extends Member {
    private String customerCode;

    public Customer() {
    }

    public Customer(String email, String password, String firstName, String lastName, String address,
                    LocalDate birthOfDate, Gender gender, String phone, String customerCode) {
        super(email, password, firstName, lastName, address, birthOfDate, gender, phone);
        this.customerCode = customerCode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
