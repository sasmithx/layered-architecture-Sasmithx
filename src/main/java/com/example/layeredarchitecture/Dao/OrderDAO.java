package com.example.layeredarchitecture.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO {
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException;
    public void selectOrderId(String orderId) throws SQLException, ClassNotFoundException;
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId) throws SQLException;
}
