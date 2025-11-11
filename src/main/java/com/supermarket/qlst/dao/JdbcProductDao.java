package com.supermarket.qlst.dao;

import com.supermarket.qlst.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcProductDao implements ProductDao {

    private static final String INSERT_OR_UPDATE_SQL =
            "INSERT INTO products (product_code, name, product_type, brand, unit, selling_price, stock_quantity, description) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE name = VALUES(name), product_type = VALUES(product_type), brand = VALUES(brand), "
                    + "unit = VALUES(unit), selling_price = VALUES(selling_price), stock_quantity = VALUES(stock_quantity), description = VALUES(description)";

    private static final String SELECT_ALL_SQL =
            "SELECT product_code, name, product_type, brand, unit, selling_price, stock_quantity, description FROM products ORDER BY name";

    private static final String SELECT_BY_CODE_SQL =
            "SELECT product_code, name, product_type, brand, unit, selling_price, stock_quantity, description FROM products WHERE product_code = ?";

    private static final String SEARCH_BY_NAME_SQL =
            "SELECT product_code, name, product_type, brand, unit, selling_price, stock_quantity, description FROM products WHERE LOWER(name) LIKE ? ORDER BY name";

    private final DatabaseConnectionManager connectionManager;

    public JdbcProductDao(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Product save(Product product) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_OR_UPDATE_SQL)) {
            statement.setString(1, product.getProductCode());
            statement.setString(2, product.getName());
            statement.setString(3, product.getProductType());
            statement.setString(4, product.getBrand());
            statement.setString(5, product.getUnit());
            statement.setDouble(6, product.getSellingPrice());
            statement.setInt(7, product.getStockQuantity());
            statement.setString(8, product.getDescription());
            statement.executeUpdate();
            return product;
        } catch (SQLException ex) {
            throw new DaoException("Failed to persist product", ex);
        }
    }

    @Override
    public Optional<Product> findByCode(String productCode) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_CODE_SQL)) {
            statement.setString(1, productCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DaoException("Failed to load product", ex);
        }
    }

    @Override
    public List<Product> findAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(mapRow(resultSet));
            }
            return products;
        } catch (SQLException ex) {
            throw new DaoException("Failed to load products", ex);
        }
    }

    @Override
    public List<Product> searchByName(String keyword) {
        String sanitized = keyword == null ? "" : keyword.trim().toLowerCase();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SEARCH_BY_NAME_SQL)) {
            statement.setString(1, '%' + sanitized + '%');
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    products.add(mapRow(resultSet));
                }
                return products;
            }
        } catch (SQLException ex) {
            throw new DaoException("Failed to search products", ex);
        }
    }

    private Product mapRow(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductCode(resultSet.getString("product_code"));
        product.setName(resultSet.getString("name"));
        product.setProductType(resultSet.getString("product_type"));
        product.setBrand(resultSet.getString("brand"));
        product.setUnit(resultSet.getString("unit"));
        product.setSellingPrice(resultSet.getDouble("selling_price"));
        product.setStockQuantity(resultSet.getInt("stock_quantity"));
        product.setDescription(resultSet.getString("description"));
        return product;
    }
}
