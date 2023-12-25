package com.example.layeredarchitecture.dao.custom;



import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    boolean save(String orderId, OrderDetail detail) throws SQLException, ClassNotFoundException;
}
