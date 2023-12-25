package com.example.layeredarchitecture.view.dao.custom.impl;



import com.example.layeredarchitecture.view.dao.SQLUtil;
import com.example.layeredarchitecture.view.dao.custom.OrderDAO;
import com.example.layeredarchitecture.view.entity.Order;
import com.example.layeredarchitecture.view.util.TransactionConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        return stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");*/
        return SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
    }

    @Override
    public Order search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
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
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {

        /*Connection connection = TransactionConnection.setAutoCommitFalse();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);*/
        boolean isExecuted = SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                entity.getOrderId(),
                entity.getOrderDate(),
                entity.getCustomerId()
        );

        if (!isExecuted) {
            TransactionConnection.rollBack();
            TransactionConnection.getConnection().setAutoCommit(true);
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
