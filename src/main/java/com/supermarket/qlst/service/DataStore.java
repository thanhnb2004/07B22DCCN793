package com.supermarket.qlst.service;

import com.supermarket.qlst.model.Customer;
import com.supermarket.qlst.model.Product;
import com.supermarket.qlst.model.Supplier;
import com.supermarket.qlst.model.WarehouseStaff;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private final ConcurrentMap<String, Customer> customers = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Supplier> suppliers = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Product> products = new ConcurrentHashMap<>();
    private final WarehouseStaff defaultWarehouseStaff;

    private DataStore() {
        defaultWarehouseStaff = new WarehouseStaff();
        defaultWarehouseStaff.setStaffCode("WH001");
        defaultWarehouseStaff.setPosition("Warehouse Staff");
        defaultWarehouseStaff.setFirstName("Kho");
        defaultWarehouseStaff.setLastName("Nhân viên");
        defaultWarehouseStaff.setEmail("warehouse@example.com");
        defaultWarehouseStaff.setBirthOfDate(LocalDate.now());

        Supplier samsung = new Supplier("NCC001", "Samsung", "0100100100", "Hà Nội", "0123456789", "samsung@example.com", "");
        Supplier lg = new Supplier("NCC002", "LG", "0100200200", "Hồ Chí Minh", "0987654321", "lg@example.com", "");
        suppliers.put(samsung.getSupplierCode(), samsung);
        suppliers.put(lg.getSupplierCode(), lg);

        Product tv = new Product("PRD001", "Tivi 55 inch", "Tivi", "Samsung", "Chiếc", 15000000, 10, "Tivi 4K");
        Product fridge = new Product("PRD002", "Tủ lạnh", "Gia dụng", "LG", "Chiếc", 12000000, 5, "Tủ lạnh Inverter");
        products.put(tv.getProductCode(), tv);
        products.put(fridge.getProductCode(), fridge);
    }

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public WarehouseStaff getDefaultWarehouseStaff() {
        return defaultWarehouseStaff;
    }

    public Customer saveCustomer(Customer customer) {
        customers.put(customer.getEmail(), customer);
        return customer;
    }

    public Customer findCustomerByEmail(String email) {
        return customers.get(email);
    }

    public List<Supplier> findAllSuppliers() {
        return new ArrayList<>(suppliers.values());
    }

    public Supplier saveSupplier(Supplier supplier) {
        suppliers.put(supplier.getSupplierCode(), supplier);
        return supplier;
    }

    public Supplier findSupplierByCode(String supplierCode) {
        return suppliers.get(supplierCode);
    }

    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product saveProduct(Product product) {
        products.put(product.getProductCode(), product);
        return product;
    }

    public Product findProductByCode(String productCode) {
        return products.get(productCode);
    }

    public List<Product> searchProductsByName(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAllProducts();
        }
        String lower = keyword.toLowerCase();
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName() != null && product.getName().toLowerCase().contains(lower)) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Supplier> searchSuppliersByName(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAllSuppliers();
        }
        String lower = keyword.toLowerCase();
        List<Supplier> result = new ArrayList<>();
        for (Supplier supplier : suppliers.values()) {
            if (supplier.getLegalName() != null && supplier.getLegalName().toLowerCase().contains(lower)) {
                result.add(supplier);
            }
        }
        return result;
    }

    public List<Customer> getAllCustomers() {
        return Collections.unmodifiableList(new ArrayList<>(customers.values()));
    }
}
