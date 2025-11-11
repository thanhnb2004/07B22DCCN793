package com.supermarket.qlst.dao;

import com.supermarket.qlst.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierDao {

    Supplier save(Supplier supplier);

    Optional<Supplier> findByCode(String supplierCode);

    List<Supplier> findAll();

    List<Supplier> searchByName(String keyword);
}
