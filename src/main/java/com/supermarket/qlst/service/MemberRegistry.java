package com.supermarket.qlst.service;

import com.supermarket.qlst.model.Customer;
import com.supermarket.qlst.model.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberRegistry {
    private final DataStore dataStore = DataStore.getInstance();

    public Customer registerMembership(String email, String password, String firstName, String lastName,
                                       String address, LocalDate birthOfDate, Gender gender, String phone) {
        Customer existing = dataStore.findCustomerByEmail(email);
        if (existing != null) {
            throw new IllegalArgumentException("Thông tin tài khoản đã tồn tại");
        }
        String customerCode = "CUS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Customer customer = new Customer(email, password, firstName, lastName, address, birthOfDate, gender, phone, customerCode);
        dataStore.saveCustomer(customer);
        return customer;
    }

    public List<String> validateRegistration(String email, String password, String confirmPassword, String firstName,
                                             String lastName, String address, LocalDate birthOfDate, Gender gender, String phone) {
        List<String> errors = new ArrayList<>();
        if (email == null || email.isBlank()) {
            errors.add("Email không được để trống");
        }
        if (password == null || password.isBlank()) {
            errors.add("Mật khẩu không được để trống");
        }
        if (confirmPassword == null || !confirmPassword.equals(password)) {
            errors.add("Mật khẩu xác nhận không khớp");
        }
        if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank()) {
            errors.add("Họ tên không được để trống");
        }
        if (phone == null || phone.isBlank()) {
            errors.add("Số điện thoại không được để trống");
        }
        if (gender == null) {
            errors.add("Vui lòng chọn giới tính");
        }
        if (birthOfDate == null) {
            errors.add("Ngày sinh không hợp lệ");
        }
        if (address == null || address.isBlank()) {
            errors.add("Địa chỉ không được để trống");
        }
        return errors;
    }
}
