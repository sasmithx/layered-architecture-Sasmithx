package com.example.layeredarchitecture.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO {
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException;
}
