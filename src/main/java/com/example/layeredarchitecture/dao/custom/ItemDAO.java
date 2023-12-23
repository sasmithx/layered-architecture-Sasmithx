package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemDTO> {

    @Override
    ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException;

    @Override
    boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    @Override
    boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    @Override
    boolean exist(String code) throws SQLException, ClassNotFoundException;

    @Override
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    @Override
    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    @Override
    ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException;
}
