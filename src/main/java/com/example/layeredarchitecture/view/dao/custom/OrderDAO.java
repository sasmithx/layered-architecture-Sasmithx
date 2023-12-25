package com.example.layeredarchitecture.view.dao.custom;



import com.example.layeredarchitecture.view.dao.CrudDAO;
import com.example.layeredarchitecture.view.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {

    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    boolean save(Order entity) throws SQLException, ClassNotFoundException;

    void selectOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
