package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.model.CustomerOrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
