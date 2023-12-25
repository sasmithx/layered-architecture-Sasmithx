package lk.sasax.layeredarchitecture.dao.custom.impl;



import lk.sasax.layeredarchitecture.dao.SQLUtil;
import lk.sasax.layeredarchitecture.dao.custom.CustomerDAO;
import lk.sasax.layeredarchitecture.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<Customer> allCustomer = new ArrayList<>();

        while (rst.next()) {
            Customer customer = new Customer(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );

            allCustomer.add(customer);
        }
        return allCustomer;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customerDTO.getId());
        pstm.setString(2, customerDTO.getName());
        pstm.setString(3, customerDTO.getAddress());*/

        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                entity.getId(),
                entity.getName(),
                entity.getAddress()
        );

    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setString(3, customerDTO.getId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                entity.getName(),
                entity.getAddress(),
                entity.getId()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/
        ResultSet resultSet = SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return resultSet.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        return rst;*/
        return SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    }

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);

        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
        }
        return null;
    }
}
