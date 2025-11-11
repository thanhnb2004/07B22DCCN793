package com.supermarket.qlst.service;

import com.supermarket.qlst.model.Supplier;

import java.util.List;

public class SupplierDirectory {
    private final DataStore dataStore = DataStore.getInstance();

    public List<Supplier> getAllSuppliers() {
        return dataStore.findAllSuppliers();
    }

    public List<Supplier> searchSuppliers(String keyword) {
        return dataStore.searchSuppliersByName(keyword);
    }

    public Supplier saveSupplier(Supplier supplier) {
        return dataStore.saveSupplier(supplier);
    }

    public Supplier findSupplier(String supplierCode) {
        return dataStore.findSupplierByCode(supplierCode);
    }
}
