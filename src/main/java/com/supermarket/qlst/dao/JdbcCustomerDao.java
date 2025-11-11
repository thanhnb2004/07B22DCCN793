package com.supermarket.qlst.dao;

import com.supermarket.qlst.model.Customer;
import com.supermarket.qlst.model.Gender;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerDao implements CustomerDao {

    private static final String INSERT_OR_UPDATE_SQL =
            "INSERT INTO customers (email, password, first_name, last_name, address, birth_of_date, gender, phone, customer_code) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE password = VALUES(password), first_name = VALUES(first_name), "
                    + "last_name = VALUES(last_name), address = VALUES(address), birth_of_date = VALUES(birth_of_date), "
                    + "gender = VALUES(gender), phone = VALUES(phone), customer_code = VALUES(customer_code)";

    private static final String SELECT_BY_EMAIL_SQL =
            "SELECT email, password, first_name, last_name, address, birth_of_date, gender, phone, customer_code "
                    + "FROM customers WHERE email = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT email, password, first_name, last_name, address, birth_of_date, gender, phone, customer_code "
                    + "FROM customers ORDER BY last_name, first_name";

    private final DatabaseConnectionManager connectionManager;

    public JdbcCustomerDao(DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Customer save(Customer customer) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_OR_UPDATE_SQL)) {
            statement.setString(1, customer.getEmail());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getFirstName());
            statement.setString(4, customer.getLastName());
            statement.setString(5, customer.getAddress());
            if (customer.getBirthOfDate() != null) {
                statement.setDate(6, Date.valueOf(customer.getBirthOfDate()));
            } else {
                statement.setDate(6, null);
            }
            statement.setString(7, customer.getGender() != null ? customer.getGender().name() : null);
            statement.setString(8, customer.getPhone());
            statement.setString(9, customer.getCustomerCode());
            statement.executeUpdate();
            return customer;
        } catch (SQLException ex) {
            throw new DaoException("Failed to persist customer", ex);
        }
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_SQL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException ex) {
            throw new DaoException("Failed to load customer", ex);
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Customer> customers = new ArrayList<>();
            while (resultSet.next()) {
                customers.add(mapRow(resultSet));
            }
            return customers;
        } catch (SQLException ex) {
            throw new DaoException("Failed to load customers", ex);
        }
    }

    private Customer mapRow(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setEmail(resultSet.getString("email"));
        customer.setPassword(resultSet.getString("password"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setAddress(resultSet.getString("address"));
        Date birthDate = resultSet.getDate("birth_of_date");
        if (birthDate != null) {
            customer.setBirthOfDate(birthDate.toLocalDate());
        }
        String gender = resultSet.getString("gender");
        if (gender != null && !gender.isBlank()) {
            customer.setGender(Gender.valueOf(gender));
        }
        customer.setPhone(resultSet.getString("phone"));
        customer.setCustomerCode(resultSet.getString("customer_code"));
        return customer;
    }
}
