package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.TransactionConnection;

import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean save(String orderId, OrderDetailDTO detail) throws SQLException, ClassNotFoundException {

        /*Connection connection = TransactionConnection.getConnection();

        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");

        stm.setString(1, orderId);
        stm.setString(2, detail.getItemCode());
        stm.setBigDecimal(3, detail.getUnitPrice());
        stm.setInt(4, detail.getQty());*/
        boolean isExecuted = SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                orderId,
                detail.getItemCode(),
                detail.getUnitPrice(),
                detail.getQty()
        );

        if (!isExecuted) {
            TransactionConnection.getConnection().rollback();
            TransactionConnection.getConnection().setAutoCommit(true);
            return false;
        }
        return true;
    }
}
