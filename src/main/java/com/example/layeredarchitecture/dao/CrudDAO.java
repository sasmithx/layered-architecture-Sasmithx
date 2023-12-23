package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    T search(String newValue) throws SQLException, ClassNotFoundException;
}

