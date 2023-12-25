package com.example.layeredarchitecture.view.dao.custom;



import com.example.layeredarchitecture.view.dao.CrudDAO;
import com.example.layeredarchitecture.view.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    boolean save(String orderId, OrderDetail detail) throws SQLException, ClassNotFoundException;
}
