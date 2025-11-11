package com.supermarket.qlst.dao;

/**
 * Runtime exception wrapper used to signal failures while interacting with the persistence layer.
 */
public class DaoException extends RuntimeException {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
