package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO {
     boolean updateItem(ItemDTO c1)throws SQLException, ClassNotFoundException;

     ItemDTO getItem(String itemcode)throws SQLException, ClassNotFoundException;

    List<ItemDTO> movableItem()throws SQLException, ClassNotFoundException;

    boolean addItem(ItemDTO c1)throws SQLException, ClassNotFoundException;

    boolean deleteItem(String text)throws SQLException, ClassNotFoundException;
}
