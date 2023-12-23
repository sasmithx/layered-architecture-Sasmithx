package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.*;
import com.example.layeredarchitecture.dao.custom.impl.*;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.TransactionConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBO{
    CustomerDAO customerDAO = new CustomerDAOImpl();
    ItemDAO itemDAO = new ItemDAOImpl();
    OrderDAO orderDAO = new OrderDAOImpl();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/

        boolean isOrderSaved;
        boolean isOrderDetailSaved = false;
        boolean isItemUpdated = false;

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderDate(orderDate);
        orderDTO.setCustomerId(customerId);

        orderDAO.selectOrderId(orderId);

        TransactionConnection.getConnection().setAutoCommit(false);
        isOrderSaved = orderDAO.save(orderDTO);

        for (OrderDetailDTO detail : orderDetails) {
            isOrderDetailSaved = orderDetailDAO.save(orderId, detail);

            //Search & Update Item
            ItemDTO item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            isItemUpdated = itemDAO.updateItem(item);
        }

        if (isOrderSaved && isOrderDetailSaved && isItemUpdated) {
            TransactionConnection.getConnection().commit();
            TransactionConnection.getConnection().setAutoCommit(true);
            return true;
        }
        return false;
    }

    public ItemDTO findItem(String code) {
        try {
            ItemDTO dto = itemDAO.findItem(code);

            return new ItemDTO(code,
                    dto.getDescription(),
                    dto.getUnitPrice(),
                    dto.getQtyOnHand()
            );

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerDTO searchCustomer(String newValue) throws SQLException, ClassNotFoundException {
        return customerDAO.searchCustomer(newValue);
    }

    @Override
    public ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.findItem(newItemCode);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.existCustomer(id);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exitItem(code);
    }

    @Override
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCustomer();
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.loadAllItems();
    }

}
