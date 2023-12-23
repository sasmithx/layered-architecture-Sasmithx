package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO> {

    @Override
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    @Override
    boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    @Override
    boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    @Override
    boolean exist(String id) throws SQLException, ClassNotFoundException;

    @Override
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    @Override
    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    @Override
    CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
}
