package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO {

    boolean save(String orderId, OrderDetailDTO detail) throws SQLException, ClassNotFoundException;
}
