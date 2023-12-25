package com.example.layeredarchitecture.view.bo.custom.impl;



import com.example.layeredarchitecture.view.bo.custom.QueryBO;
import com.example.layeredarchitecture.view.dao.DAOFactory;
import com.example.layeredarchitecture.view.dao.custom.QueryDAO;
import com.example.layeredarchitecture.view.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.List;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException {
        return queryDAO.customerOrderDetails();
    }
}
