package com.supermarket.qlst.dao;

import java.util.Objects;

/**
 * Centralizes configuration properties required to open JDBC connections. The configuration can
 * be supplied via environment variables or JVM system properties so the application can be easily
 * tuned for different deployment environments without code changes.
 */
public final class DatabaseConfig {

    private static final String DEFAULT_JDBC_URL =
            "jdbc:mysql://localhost:3306/qlst?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DEFAULT_USERNAME = "qlst";
    private static final String DEFAULT_PASSWORD = "qlst";

    private DatabaseConfig() {
    }

    public static String getJdbcUrl() {
        return lookup("QLST_JDBC_URL", "qlst.jdbc.url", DEFAULT_JDBC_URL);
    }

    public static String getUsername() {
        return lookup("QLST_JDBC_USERNAME", "qlst.jdbc.username", DEFAULT_USERNAME);
    }

    public static String getPassword() {
        return lookup("QLST_JDBC_PASSWORD", "qlst.jdbc.password", DEFAULT_PASSWORD);
    }

    private static String lookup(String envKey, String propertyKey, String defaultValue) {
        String value = System.getenv(envKey);
        if (value == null || value.isBlank()) {
            value = System.getProperty(propertyKey);
        }
        if (value == null || value.isBlank()) {
            value = defaultValue;
        }
        return Objects.requireNonNull(value);
    }
}
