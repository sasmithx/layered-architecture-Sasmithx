package com.example.layeredarchitecture.view.dao.custom;



import com.example.layeredarchitecture.view.dao.SuperDAO;
import com.example.layeredarchitecture.view.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
