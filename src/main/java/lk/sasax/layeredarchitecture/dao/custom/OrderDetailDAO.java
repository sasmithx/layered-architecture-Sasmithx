package lk.sasax.layeredarchitecture.dao.custom;



import lk.sasax.layeredarchitecture.dao.CrudDAO;
import lk.sasax.layeredarchitecture.entity.OrderDetail;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {

    boolean save(String orderId, OrderDetail detail) throws SQLException, ClassNotFoundException;
}
