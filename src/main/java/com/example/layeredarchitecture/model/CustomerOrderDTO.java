package com.example.layeredarchitecture.model;

/**
* A CustomDTO Created (named CustomerOrder)
* Inorder to set and get
* Custom Query data via QueryDAOImpl
*/

public class CustomerOrderDTO {
    private String id;
    private String name;
    private String date;
    private String total;

    public CustomerOrderDTO() {
    }

    public CustomerOrderDTO(String id, String name, String date, String total) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CustomDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
