package com.example.layeredarchitecture.Dao;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> allCustomer = new ArrayList<>();

        while(rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address"));
            allCustomer.add(customerDTO);
        }
        return allCustomer;

    }



    @Override
    public boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", dto.getId(),dto.getName(),dto.getAddress());

    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {

       return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?", dto.getName(), dto.getAddress(), dto.getId());
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM Customer WHERE id=?", id);

    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    }
    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
    }
    @Override
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);
        CustomerDTO dto = null;
        while (rst.next()){
            new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return dto;
    }
}
