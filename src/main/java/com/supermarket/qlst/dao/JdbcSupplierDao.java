package com.supermarket.qlst.dao;

import com.supermarket.qlst.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSupplierDao implements SupplierDao {

    private static final String INSERT_OR_UPDATE_SQL =
            "INSERT INTO suppliers (supplier_code, legal_name, tax_code, address, phone, email, note) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE legal_name = VALUES(legal_name), tax_code = VALUES(tax_code), "
                    + "address = VALUES(address), phone = VALUES(phone), email = VALUES(email), note = VALUES(note)";

    private static final String SELECT_ALL_SQL =
            "SELECT supplier_code, legal_name, tax_code, address, phone, email, note FROM suppliers ORDER BY legal_name";

    private static final String SELECT_BY_CODE_SQL =
            "SELECT supplier_code, legal_name, tax_code, address, phone, email, note FROM suppliers WHERE supplier_code = ?";

    private static final String SEARCH_BY_NAME_SQL =
            "SELECT supplier_code, legal_name, tax_code, address, phone, email, note FROM suppliers WHERE LOWER(legal_name) LIKE ? ORDER BY legal_name";

    private final DatabaseConnectionManager connectionManager;

    public JdbcSupplierDao(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Supplier save(Supplier supplier) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_OR_UPDATE_SQL)) {
            statement.setString(1, supplier.getSupplierCode());
            statement.setString(2, supplier.getLegalName());
            statement.setString(3, supplier.getTaxCode());
            statement.setString(4, supplier.getAddress());
            statement.setString(5, supplier.getPhone());
            statement.setString(6, supplier.getEmail());
            statement.setString(7, supplier.getNote());
            statement.executeUpdate();
            return supplier;
        } catch (SQLException ex) {
            throw new DaoException("Failed to persist supplier", ex);
        }
    }

    @Override
    public Optional<Supplier> findByCode(String supplierCode) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_CODE_SQL)) {
            statement.setString(1, supplierCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DaoException("Failed to load supplier", ex);
        }
    }

    @Override
    public List<Supplier> findAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Supplier> suppliers = new ArrayList<>();
            while (resultSet.next()) {
                suppliers.add(mapRow(resultSet));
            }
            return suppliers;
        } catch (SQLException ex) {
            throw new DaoException("Failed to load suppliers", ex);
        }
    }

    @Override
    public List<Supplier> searchByName(String keyword) {
        String sanitized = keyword == null ? "" : keyword.trim().toLowerCase();
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SEARCH_BY_NAME_SQL)) {
            statement.setString(1, '%' + sanitized + '%');
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Supplier> suppliers = new ArrayList<>();
                while (resultSet.next()) {
                    suppliers.add(mapRow(resultSet));
                }
                return suppliers;
            }
        } catch (SQLException ex) {
            throw new DaoException("Failed to search suppliers", ex);
        }
    }

    private Supplier mapRow(ResultSet resultSet) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setSupplierCode(resultSet.getString("supplier_code"));
        supplier.setLegalName(resultSet.getString("legal_name"));
        supplier.setTaxCode(resultSet.getString("tax_code"));
        supplier.setAddress(resultSet.getString("address"));
        supplier.setPhone(resultSet.getString("phone"));
        supplier.setEmail(resultSet.getString("email"));
        supplier.setNote(resultSet.getString("note"));
        return supplier;
    }
}
