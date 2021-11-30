package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.dto.OrdreDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PurchaseOrderBO extends SuperBO {
    //transetion methods
    boolean placeOrder(OrderDTO orderDTO);
    boolean saveOrderDetail(String orderId, ArrayList<OrdreDetailsDTO> items) throws SQLException, ClassNotFoundException;
    boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
    ///////////////////////////////////////////////////
     List<String> getAllItemIds() throws SQLException, ClassNotFoundException;
     List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
     ItemDTO getItemData(String itemcode) throws SQLException, ClassNotFoundException;
     String getOrderId()throws SQLException, ClassNotFoundException;
}
