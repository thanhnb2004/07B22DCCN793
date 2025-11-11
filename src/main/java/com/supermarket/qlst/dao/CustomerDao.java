package com.supermarket.qlst.dao;

import com.supermarket.qlst.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    Customer save(Customer customer);

    Optional<Customer> findByEmail(String email);

    List<Customer> findAll();
}
