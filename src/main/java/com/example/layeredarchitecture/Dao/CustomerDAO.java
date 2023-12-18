package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    ResultSet generateNewId() throws SQLException, ClassNotFoundException;
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
    //public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
}
