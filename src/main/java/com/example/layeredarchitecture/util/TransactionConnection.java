package com.example.layeredarchitecture.util;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionConnection {
    private static Connection connection;

    public static Connection setConnection() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getDbConnection().getConnection();
        return connection;
    }

    public static Connection setAutoCommitFalse() throws SQLException {
        connection.setAutoCommit(false);
        return connection;
    }

    public static Connection setAutoCommitTrue() throws SQLException {
        connection.setAutoCommit(true);
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }
}
