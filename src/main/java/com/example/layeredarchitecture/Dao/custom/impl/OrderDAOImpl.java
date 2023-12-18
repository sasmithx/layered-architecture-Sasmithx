package com.example.layeredarchitecture.Dao.custom.impl;

import com.example.layeredarchitecture.Dao.custom.OrderDAO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
    }
    @Override
    public boolean selectOrderId(String orderId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
    }
    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)", orderId, Date.valueOf(orderDate), customerId);
    }
}
