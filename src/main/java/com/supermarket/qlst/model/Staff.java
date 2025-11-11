package com.supermarket.qlst.model;

import java.time.LocalDate;

public class Staff extends Member {
    private String position;
    private String staffCode;

    public Staff() {
    }

    public Staff(String email, String password, String firstName, String lastName, String address,
                 LocalDate birthOfDate, Gender gender, String phone, String position, String staffCode) {
        super(email, password, firstName, lastName, address, birthOfDate, gender, phone);
        this.position = position;
        this.staffCode = staffCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}
