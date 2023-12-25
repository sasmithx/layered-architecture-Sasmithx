package lk.sasax.layeredarchitecture.bo.custom;


import lk.sasax.layeredarchitecture.bo.SuperBO;
import lk.sasax.layeredarchitecture.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface QueryBO extends SuperBO {

    List<CustomerOrderDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}
