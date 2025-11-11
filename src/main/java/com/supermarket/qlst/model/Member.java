package com.supermarket.qlst.model;

import java.time.LocalDate;
import java.util.Objects;

public class Member {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate birthOfDate;
    private Gender gender;
    private String phone;

    public Member() {
    }

    public Member(String email, String password, String firstName, String lastName,
                  String address, LocalDate birthOfDate, Gender gender, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthOfDate = birthOfDate;
        this.gender = gender;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(LocalDate birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        StringBuilder builder = new StringBuilder();
        if (lastName != null && !lastName.isBlank()) {
            builder.append(lastName.trim());
        }
        if (firstName != null && !firstName.isBlank()) {
            if (builder.length() > 0) {
                builder.append(' ');
            }
            builder.append(firstName.trim());
        }
        return builder.length() == 0 ? null : builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
