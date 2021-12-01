package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {
    @Id
    private String ItemCode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;
    private int FirstQtyOnHand;

    @OneToMany(mappedBy = "item" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetails> orderdetails = new ArrayList<>();

    public Item() {
    }


    public Item(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, int firstQtyOnHand) {
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        FirstQtyOnHand = firstQtyOnHand;
    }

    public Item(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, int firstQtyOnHand, List<OrderDetails> orderdetails) {
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        FirstQtyOnHand = firstQtyOnHand;
        this.orderdetails = orderdetails;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPackSize() {
        return PackSize;
    }

    public void setPackSize(String packSize) {
        PackSize = packSize;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQtyOnHand() {
        return QtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        QtyOnHand = qtyOnHand;
    }

    public int getFirstQtyOnHand() {
        return FirstQtyOnHand;
    }

    public void setFirstQtyOnHand(int firstQtyOnHand) {
        FirstQtyOnHand = firstQtyOnHand;
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

    @Override
    public String toString() {
        return "Item{" +
                "ItemCode='" + ItemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", PackSize='" + PackSize + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", QtyOnHand=" + QtyOnHand +
                ", FirstQtyOnHand=" + FirstQtyOnHand +
                ", orderdetails=" + orderdetails +
                '}';
    }
}
