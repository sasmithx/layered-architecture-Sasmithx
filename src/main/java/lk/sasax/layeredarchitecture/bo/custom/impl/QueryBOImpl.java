package lk.sasax.layeredarchitecture.bo.custom.impl;



import lk.sasax.layeredarchitecture.bo.custom.QueryBO;
import lk.sasax.layeredarchitecture.dao.DAOFactory;
import lk.sasax.layeredarchitecture.dao.custom.QueryDAO;
import lk.sasax.layeredarchitecture.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.List;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException {
        return queryDAO.customerOrderDetails();
    }
}
