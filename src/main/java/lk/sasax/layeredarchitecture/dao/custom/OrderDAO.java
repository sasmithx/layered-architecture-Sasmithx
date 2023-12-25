package lk.sasax.layeredarchitecture.dao.custom;



import lk.sasax.layeredarchitecture.dao.CrudDAO;
import lk.sasax.layeredarchitecture.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order> {

    ResultSet generateNewId() throws SQLException, ClassNotFoundException;

    boolean save(Order entity) throws SQLException, ClassNotFoundException;

    void selectOrderId(String orderId) throws SQLException, ClassNotFoundException;
}
