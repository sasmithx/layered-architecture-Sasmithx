package lk.sasax.layeredarchitecture.dao;


import lk.sasax.layeredarchitecture.dao.custom.impl.*;
import lk.sasax.layeredarchitecture.dao.custom.impl.*;
//import com.example.layeredarchitecture.view.dao.custom.impl.*;

public class DAOFactory {
    //Singleton

    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    //Create types

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, QUERY
    }

    //Return DAOImpl objects

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
