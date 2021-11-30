package lk.ijse.hibernate.dto;

public class OrdreDetailsDTO {
    private String OrderID;
    private String ItemID;
    private int OrderQTY;
    private double Discount;

    public OrdreDetailsDTO() {
    }

    public OrdreDetailsDTO(String orderID, String itemID, int orderQTY, double discount) {
        OrderID = orderID;
        ItemID = itemID;
        OrderQTY = orderQTY;
        Discount = discount;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
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
}
