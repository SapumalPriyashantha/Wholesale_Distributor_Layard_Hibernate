package lk.ijse.hibernate.dto;

import java.util.ArrayList;

public class OrderDTO {
    private String OrderID;
    private String Orderdate;
    private String CustID;
    private double cost;
    private ArrayList<OrdreDetailsDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String orderdate, String custID, double cost) {
        OrderID = orderID;
        Orderdate = orderdate;
        CustID = custID;
        this.cost = cost;
    }

    public OrderDTO(String orderID, String orderdate, String custID, double cost, ArrayList<OrdreDetailsDTO> items) {
        OrderID = orderID;
        Orderdate = orderdate;
        CustID = custID;
        this.cost = cost;
        this.items = items;
    }

    public OrderDTO(String orderID, String orderdate, String custID) {
        OrderID = orderID;
        Orderdate = orderdate;
        CustID =  custID;
    }

    public OrderDTO(String orderID, String orderdate, double cost) {
        OrderID = orderID;
        Orderdate = orderdate;
        this.cost = cost;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getOrderdate() {
        return Orderdate;
    }

    public void setOrderdate(String orderdate) {
        Orderdate = orderdate;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<OrdreDetailsDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrdreDetailsDTO> items) {
        this.items = items;
    }
}
