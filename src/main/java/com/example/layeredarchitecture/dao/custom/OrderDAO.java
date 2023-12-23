package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public interface OrderDAO {

    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    void selectOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
