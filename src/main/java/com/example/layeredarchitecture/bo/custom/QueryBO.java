package com.example.layeredarchitecture.bo.custom;


import com.example.layeredarchitecture.bo.SuperBO;
import com.example.layeredarchitecture.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {

    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
