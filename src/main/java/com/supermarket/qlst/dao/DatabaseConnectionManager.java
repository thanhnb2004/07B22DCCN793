package com.supermarket.qlst.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides a single point for acquiring JDBC connections. The manager exposes a lightweight
 * singleton wrapper so callers do not need to repeat configuration lookups each time a connection
 * is required.
 */
public final class DatabaseConnectionManager {

    private static final DatabaseConnectionManager INSTANCE = new DatabaseConnectionManager();

    private final String jdbcUrl;
    private final String username;
    private final String password;

    private DatabaseConnectionManager() {
        this.jdbcUrl = DatabaseConfig.getJdbcUrl();
        this.username = DatabaseConfig.getUsername();
        this.password = DatabaseConfig.getPassword();
    }

    public static DatabaseConnectionManager getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        if (jdbcUrl == null || jdbcUrl.isBlank()) {
            throw new SQLException("JDBC URL is not configured");
        }
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    public boolean isConfigured() {
        return jdbcUrl != null && !jdbcUrl.isBlank();
    }
}
