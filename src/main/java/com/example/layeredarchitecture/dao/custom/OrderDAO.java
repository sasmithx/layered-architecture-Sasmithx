package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public interface OrderDAO extends SuperDAO {

    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;

    void selectOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
