package com.example.layeredarchitecture.Dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

public interface QueryDAO {
    void customerOrderDetails(CustomerDTO customerDTO);
}
