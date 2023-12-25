package lk.sasax.layeredarchitecture.dao.custom;



import lk.sasax.layeredarchitecture.dto.CustomerOrderDTO;
import lk.sasax.layeredarchitecture.dao.SuperDAO;

import java.sql.SQLException;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
