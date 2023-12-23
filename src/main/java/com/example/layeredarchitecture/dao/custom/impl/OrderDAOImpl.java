package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.util.TransactionConnection;

import java.sql.*;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        return stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");*/
        return SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
    }

    @Override
    public void selectOrderId(String orderId) throws SQLException, ClassNotFoundException {
        /*Connection connection = TransactionConnection.setConnection();

        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        *//*if order id already exist*//*
        if (stm.executeQuery().next()) {

        }*/
        ResultSet resultSet = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);
        resultSet.next();
    }

    @Override
    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {

        /*Connection connection = TransactionConnection.setAutoCommitFalse();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);*/
        boolean isExecuted = SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderDTO.getOrderId(),
                orderDTO.getOrderDate(),
                orderDTO.getCustomerId()
        );

        if (!isExecuted) {
            TransactionConnection.rollBack();
            TransactionConnection.getConnection().setAutoCommit(true);
            return false;
        }
        return true;
    }
}
