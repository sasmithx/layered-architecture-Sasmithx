package com.example.layeredarchitecture.view.bo.custom;

import com.example.layeredarchitecture.view.bo.SuperBO;
import com.example.layeredarchitecture.view.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    ResultSet generateNewCustomerId() throws SQLException, ClassNotFoundException;
}
