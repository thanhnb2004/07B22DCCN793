package com.supermarket.qlst.service;

import com.supermarket.qlst.dao.CustomerDao;
import com.supermarket.qlst.dao.DatabaseConnectionManager;
import com.supermarket.qlst.dao.DaoException;
import com.supermarket.qlst.dao.JdbcCustomerDao;
import com.supermarket.qlst.dao.JdbcProductDao;
import com.supermarket.qlst.dao.JdbcSupplierDao;
import com.supermarket.qlst.dao.ProductDao;
import com.supermarket.qlst.dao.SupplierDao;
import com.supermarket.qlst.model.Customer;
import com.supermarket.qlst.model.Product;
import com.supermarket.qlst.model.Supplier;
import com.supermarket.qlst.model.WarehouseStaff;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DataStore {
    private static final DataStore INSTANCE = new DataStore();

    private static final Logger LOGGER = Logger.getLogger(DataStore.class.getName());

    private final ConcurrentMap<String, Customer> customers = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Supplier> suppliers = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Product> products = new ConcurrentHashMap<>();
    private final CustomerDao customerDao;
    private final SupplierDao supplierDao;
    private final ProductDao productDao;
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

        CustomerDao resolvedCustomerDao = null;
        SupplierDao resolvedSupplierDao = null;
        ProductDao resolvedProductDao = null;
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        try (Connection ignored = connectionManager.getConnection()) {
            resolvedCustomerDao = new JdbcCustomerDao(connectionManager);
            resolvedSupplierDao = new JdbcSupplierDao(connectionManager);
            resolvedProductDao = new JdbcProductDao(connectionManager);
            hydrateFromDatabase(resolvedCustomerDao, resolvedSupplierDao, resolvedProductDao);
        } catch (SQLException | DaoException ex) {
            LOGGER.log(Level.INFO, "Database connection unavailable, continuing with in-memory data", ex);
        }

        this.customerDao = resolvedCustomerDao;
        this.supplierDao = resolvedSupplierDao;
        this.productDao = resolvedProductDao;
    }

    public static DataStore getInstance() {
        return INSTANCE;
    }

    public WarehouseStaff getDefaultWarehouseStaff() {
        return defaultWarehouseStaff;
    }

    public Customer saveCustomer(Customer customer) {
        if (customerDao != null) {
            try {
                Customer persisted = customerDao.save(customer);
                customers.put(persisted.getEmail(), persisted);
                return persisted;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to persist customer to database", ex);
            }
        }
        customers.put(customer.getEmail(), customer);
        return customer;
    }

    public Customer findCustomerByEmail(String email) {
        if (customerDao != null) {
            try {
                return customerDao.findByEmail(email)
                        .map(customer -> {
                            customers.put(customer.getEmail(), customer);
                            return customer;
                        })
                        .orElse(customers.get(email));
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to load customer from database", ex);
            }
        }
        return customers.get(email);
    }

    public List<Supplier> findAllSuppliers() {
        if (supplierDao != null) {
            try {
                List<Supplier> loaded = supplierDao.findAll();
                for (Supplier supplier : loaded) {
                    suppliers.put(supplier.getSupplierCode(), supplier);
                }
                return loaded;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to load suppliers from database", ex);
            }
        }
        return new ArrayList<>(suppliers.values());
    }

    public Supplier saveSupplier(Supplier supplier) {
        if (supplierDao != null) {
            try {
                Supplier persisted = supplierDao.save(supplier);
                suppliers.put(persisted.getSupplierCode(), persisted);
                return persisted;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to persist supplier to database", ex);
            }
        }
        suppliers.put(supplier.getSupplierCode(), supplier);
        return supplier;
    }

    public Supplier findSupplierByCode(String supplierCode) {
        if (supplierDao != null) {
            try {
                return supplierDao.findByCode(supplierCode)
                        .map(supplier -> {
                            suppliers.put(supplier.getSupplierCode(), supplier);
                            return supplier;
                        })
                        .orElse(suppliers.get(supplierCode));
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to load supplier from database", ex);
            }
        }
        return suppliers.get(supplierCode);
    }

    public List<Product> findAllProducts() {
        if (productDao != null) {
            try {
                List<Product> loaded = productDao.findAll();
                for (Product product : loaded) {
                    products.put(product.getProductCode(), product);
                }
                return loaded;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to load products from database", ex);
            }
        }
        return new ArrayList<>(products.values());
    }

    public Product saveProduct(Product product) {
        if (productDao != null) {
            try {
                Product persisted = productDao.save(product);
                products.put(persisted.getProductCode(), persisted);
                return persisted;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to persist product to database", ex);
            }
        }
        products.put(product.getProductCode(), product);
        return product;
    }

    public Product findProductByCode(String productCode) {
        if (productDao != null) {
            try {
                return productDao.findByCode(productCode)
                        .map(product -> {
                            products.put(product.getProductCode(), product);
                            return product;
                        })
                        .orElse(products.get(productCode));
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to load product from database", ex);
            }
        }
        return products.get(productCode);
    }

    public List<Product> searchProductsByName(String keyword) {
        if (productDao != null) {
            try {
                List<Product> matches = productDao.searchByName(keyword);
                for (Product product : matches) {
                    products.put(product.getProductCode(), product);
                }
                return matches;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to search products in database", ex);
            }
        }
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
        if (supplierDao != null) {
            try {
                List<Supplier> matches = supplierDao.searchByName(keyword);
                for (Supplier supplier : matches) {
                    suppliers.put(supplier.getSupplierCode(), supplier);
                }
                return matches;
            } catch (DaoException ex) {
                LOGGER.log(Level.WARNING, "Failed to search suppliers in database", ex);
            }
        }
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

    private void hydrateFromDatabase(CustomerDao customerDao, SupplierDao supplierDao, ProductDao productDao) {
        try {
            for (Customer customer : customerDao.findAll()) {
                customers.put(customer.getEmail(), customer);
            }
        } catch (DaoException ex) {
            LOGGER.log(Level.WARNING, "Failed to preload customers from database", ex);
        }

        try {
            for (Supplier supplier : supplierDao.findAll()) {
                suppliers.put(supplier.getSupplierCode(), supplier);
            }
        } catch (DaoException ex) {
            LOGGER.log(Level.WARNING, "Failed to preload suppliers from database", ex);
        }

        try {
            for (Product product : productDao.findAll()) {
                products.put(product.getProductCode(), product);
            }
        } catch (DaoException ex) {
            LOGGER.log(Level.WARNING, "Failed to preload products from database", ex);
        }
    }
}
