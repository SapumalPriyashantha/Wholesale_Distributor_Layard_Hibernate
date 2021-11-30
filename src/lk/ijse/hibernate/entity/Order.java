package lk.ijse.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    @Id
    private String OrderID;
    private String Orderdate;
    private String CustID;
    private double cost;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetails> orderdetails = new ArrayList<>();

    public Order() {
    }
//customerwise income walata heduwe
    public Order(String orderID, String orderdate, double cost) {
        OrderID = orderID;
        Orderdate = orderdate;
        this.cost = cost;
    }

    public Order(String orderID, String orderdate, String custID, double cost) {
        OrderID = orderID;
        Orderdate = orderdate;
        CustID = custID;
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

    public List<OrderDetails> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDetails> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public void addOrderDetails(OrderDetails orderDetails) {
        this.orderdetails.add(orderDetails);
    }
}
