package com.example.layeredarchitecture.view.bo.custom.impl;



import com.example.layeredarchitecture.view.bo.custom.ItemBO;
import com.example.layeredarchitecture.view.dao.DAOFactory;
import com.example.layeredarchitecture.view.dao.custom.ItemDAO;
import com.example.layeredarchitecture.view.dto.ItemDTO;
import com.example.layeredarchitecture.view.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

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

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                itemDTO.getCode(),
                itemDTO.getDescription(),
                itemDTO.getUnitPrice(),
                itemDTO.getQtyOnHand()
        ));
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public ResultSet generateNewItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }
}
