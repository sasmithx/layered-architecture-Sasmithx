package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO extends SuperDAO {

    boolean save(String orderId, OrderDetailDTO detail) throws SQLException, ClassNotFoundException;
}
