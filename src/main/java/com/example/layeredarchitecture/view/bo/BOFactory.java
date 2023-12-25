package com.example.layeredarchitecture.view.bo;


import com.example.layeredarchitecture.view.bo.custom.impl.CustomerBOImpl;
import com.example.layeredarchitecture.view.bo.custom.impl.ItemBOImpl;
import com.example.layeredarchitecture.view.bo.custom.impl.PlaceOrderBOImpl;
import com.example.layeredarchitecture.view.bo.custom.impl.QueryBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, PLACE_ORDER, QUERY
    }

    public SuperBO getTypes(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case QUERY:
                return new QueryBOImpl();
            default:
                return null;
        }
    }
}
