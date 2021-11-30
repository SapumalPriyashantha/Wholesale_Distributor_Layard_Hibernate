package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Orders")
public class Order {
    @Id
    private String OrderID;
    private String Orderdate;
    private double cost;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public Order() {
    }
//customerwise income walata heduwe
    public Order(String OrderID, String Orderdate, double cost) {
        this.OrderID = OrderID;
        this.Orderdate = Orderdate;
        this.cost = cost;
    }

    public Order(String OrderID, String Orderdate, double cost, Customer customer) {
        this.OrderID = OrderID;
        this.Orderdate = Orderdate;
        this.cost = cost;
        this.customer = customer;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        OrderID = OrderID;
    }

    public String getOrderdate() {
        return Orderdate;
    }

    public void setOrderdate(String Orderdate) {
        Orderdate = Orderdate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
