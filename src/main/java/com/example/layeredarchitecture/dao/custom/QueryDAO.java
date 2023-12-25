package com.example.layeredarchitecture.dao.custom;



import com.example.layeredarchitecture.dto.CustomerOrderDTO;
import com.example.layeredarchitecture.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
