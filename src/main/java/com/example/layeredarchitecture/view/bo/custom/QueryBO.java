package com.example.layeredarchitecture.view.bo.custom;


import com.example.layeredarchitecture.view.bo.SuperBO;
import com.example.layeredarchitecture.view.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {

    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
