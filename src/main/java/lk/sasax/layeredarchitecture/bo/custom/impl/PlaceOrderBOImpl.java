package lk.sasax.layeredarchitecture.bo.custom.impl;



import lk.sasax.layeredarchitecture.bo.custom.PlaceOrderBO;
import lk.sasax.layeredarchitecture.dao.DAOFactory;
import lk.sasax.layeredarchitecture.dao.custom.CustomerDAO;
import lk.sasax.layeredarchitecture.dao.custom.ItemDAO;
import lk.sasax.layeredarchitecture.dao.custom.OrderDAO;
import lk.sasax.layeredarchitecture.dto.CustomerDTO;
import lk.sasax.layeredarchitecture.entity.Customer;
import lk.sasax.layeredarchitecture.entity.Item;
import lk.sasax.layeredarchitecture.entity.Order;
import lk.sasax.layeredarchitecture.entity.OrderDetail;
import lk.sasax.layeredarchitecture.util.TransactionConnection;
import lk.sasax.layeredarchitecture.dao.custom.OrderDetailDAO;
import lk.sasax.layeredarchitecture.dto.ItemDTO;
import lk.sasax.layeredarchitecture.dto.OrderDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/

        boolean isOrderSaved;
        boolean isOrderDetailSaved = false;
        boolean isItemUpdated = false;

        Order entity = new Order();
        entity.setOrderId(orderId);
        entity.setOrderDate(orderDate);
        entity.setCustomerId(customerId);

        orderDAO.selectOrderId(orderId);

        TransactionConnection.getConnection().setAutoCommit(false);
        isOrderSaved = orderDAO.save(entity);

        for (OrderDetailDTO detail : orderDetails) {
            isOrderDetailSaved = orderDetailDAO.save(orderId, new OrderDetail(
                    detail.getItemCode(),
                    detail.getQty(),
                    detail.getUnitPrice()));

            //Search & Update Item
            ItemDTO item = findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            isItemUpdated = itemDAO.update(new Item(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()));
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
            Item entity = itemDAO.search(code);

            return new ItemDTO(code,
                    entity.getDescription(),
                    entity.getUnitPrice(),
                    entity.getQtyOnHand()
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
        Customer customer = customerDAO.search(newValue);
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress()
        );
        return customerDTO;
    }

    @Override
    public ItemDTO searchItem(String newItemCode) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(newItemCode);
        return new ItemDTO(
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand());
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public ResultSet generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress()
            ));
        }
        return customerDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(
                    item.getCode(),
                    item.getDescription(),
                    item.getUnitPrice(),
                    item.getQtyOnHand()
            ));
        }
        return itemDTOS;
    }
}
