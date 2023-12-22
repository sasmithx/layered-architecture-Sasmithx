package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.QueryDAO;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.CustomerOrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException {
        //join Query
        ResultSet resultSet = SQLUtil.execute("SELECT " +
                "o.oid as ID, " +
                "c.name as CUSTOMER, " +
                "o.date as DATE, " +
                "SUM(od.qty * od.unitPrice) as TOTAL " +
                "FROM Customer c " +
                "LEFT JOIN Orders o " +
                "ON c.id = o.customerID " +
                "LEFT JOIN OrderDetails od " +
                "ON o.oid = od.oid " +
                "GROUP BY o.oid, c.name " +
                "ORDER BY o.oid ASC "
        );

        List<CustomerOrderDTO> queryOutput = new ArrayList<>();

        while (resultSet.next()) {
            CustomerOrderDTO dto = new CustomerOrderDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

            queryOutput.add(dto);
        }
        return queryOutput;
    }
}
