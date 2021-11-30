package lk.ijse.hibernate.dto;

public class ItemDTO {
    private String ItemCode;
    private String Description;
    private String PackSize;
    private double UnitPrice;
    private int QtyOnHand;
    private int FirstQtyOnHand;

    public ItemDTO() {
    }

    public ItemDTO(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand) {
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
    }

    public ItemDTO(String itemCode, String description, String packSize, double unitPrice, int qtyOnHand, int firstQtyOnHand) {
        ItemCode = itemCode;
        Description = description;
        PackSize = packSize;
        UnitPrice = unitPrice;
        QtyOnHand = qtyOnHand;
        FirstQtyOnHand = firstQtyOnHand;
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
}
