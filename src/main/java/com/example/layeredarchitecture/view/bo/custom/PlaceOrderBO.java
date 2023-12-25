package com.example.layeredarchitecture.view.bo.custom;



import com.example.layeredarchitecture.view.bo.SuperBO;
import com.example.layeredarchitecture.view.dto.CustomerDTO;
import com.example.layeredarchitecture.view.dto.ItemDTO;
import com.example.layeredarchitecture.view.dto.OrderDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {

    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
    CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
}
