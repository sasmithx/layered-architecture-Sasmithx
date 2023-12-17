package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
     ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

     boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

     boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

     ResultSet generateId() throws SQLException, ClassNotFoundException;

     boolean existItem(String code) throws SQLException, ClassNotFoundException;

     ItemDTO findItem(String newItemCode) throws SQLException, ClassNotFoundException;
}
