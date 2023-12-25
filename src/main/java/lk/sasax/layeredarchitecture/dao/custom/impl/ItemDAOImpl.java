package lk.sasax.layeredarchitecture.dao.custom.impl;



import lk.sasax.layeredarchitecture.dao.SQLUtil;
import lk.sasax.layeredarchitecture.dao.custom.ItemDAO;
import lk.sasax.layeredarchitecture.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<Item> allItems = new ArrayList<>();

        while (rst.next()) {
            Item item = new Item(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );

            allItems.add(item);
        }

        return allItems;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, entity.getCode());
        pstm.setString(2, entity.getDescription());
        pstm.setBigDecimal(3, entity.getUnitPrice());
        pstm.setInt(4, entity.getQtyOnHand());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                entity.getCode(),
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getQtyOnHand()
        );
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, entity.getDescription());
        pstm.setBigDecimal(2, entity.getUnitPrice());
        pstm.setInt(3, entity.getQtyOnHand());
        pstm.setString(4, entity.getCode());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                entity.getDescription(),
                entity.getUnitPrice(),
                entity.getQtyOnHand(),
                entity.getCode()
        );
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");

        pstm.setString(1, code);
        return pstm.executeQuery().next();*/
        ResultSet resultSet = SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return resultSet.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM Item WHERE code=?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");

        return rst;*/
        return SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    }

    @Override
    public Item search(String newItemCode) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", newItemCode);

        if (rst.next()) {
           return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4));
        }
        return null;
    }
}
