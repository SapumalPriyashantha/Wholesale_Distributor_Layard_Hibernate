package lk.ijse.hibernate.entity;

import javax.persistence.*;

@Entity
public class OrderDetails {
//    private String OrderID;
//    private String ItemID;
//    private int OrderQTY;
//    private double Discount;
//
//    public OrderDetails() {
//    }
//
//    public OrderDetails(String orderID, String itemID, int orderQTY, double discount) {
//        OrderID = orderID;
//        ItemID = itemID;
//        OrderQTY = orderQTY;
//        Discount = discount;
//    }
//
//    public String getOrderID() {
//        return OrderID;
//    }
//
//    public void setOrderID(String orderID) {
//        OrderID = orderID;
//    }
//
//    public String getItemID() {
//        return ItemID;
//    }
//
//    public void setItemID(String itemID) {
//        ItemID = itemID;
//    }
//
//    public int getOrderQTY() {
//        return OrderQTY;
//    }
//
//    public void setOrderQTY(int orderQTY) {
//        OrderQTY = orderQTY;
//    }
//
//    public double getDiscount() {
//        return Discount;
//    }
//
//    public void setDiscount(double discount) {
//        Discount = discount;
//    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn
    private Order order;
    @ManyToOne
    @JoinColumn
    private Item item;

    private int OrderQTY;
    private double Discount;

    public OrderDetails() {
    }

    public OrderDetails(Order order, Item item, int orderQTY, double discount) {
        this.order = order;
        this.item = item;
        OrderQTY = orderQTY;
        Discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getOrderQTY() {
        return OrderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        OrderQTY = orderQTY;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", order=" + order +
                ", item=" + item +
                ", OrderQTY=" + OrderQTY +
                ", Discount=" + Discount +
                '}';
    }
}
