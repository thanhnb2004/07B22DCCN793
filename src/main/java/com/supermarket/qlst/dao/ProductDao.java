package com.supermarket.qlst.dao;

import com.supermarket.qlst.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Product save(Product product);

    Optional<Product> findByCode(String productCode);

    List<Product> findAll();

    List<Product> searchByName(String keyword);
}
