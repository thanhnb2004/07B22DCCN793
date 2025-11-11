package com.supermarket.qlst.service;

import com.supermarket.qlst.model.Product;

import java.util.List;

public class ProductCatalog {
    private final DataStore dataStore = DataStore.getInstance();

    public List<Product> searchItems(String keyword) {
        return dataStore.searchProductsByName(keyword);
    }

    public Product findByCode(String productCode) {
        return dataStore.findProductByCode(productCode);
    }

    public Product save(Product product) {
        return dataStore.saveProduct(product);
    }

    public void increaseStockQuantity(Product product, int quantity) {
        product.increaseStockQuantity(quantity);
        dataStore.saveProduct(product);
    }
}
