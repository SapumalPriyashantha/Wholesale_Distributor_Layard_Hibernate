package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
     Item getItem(String itemcode) throws SQLException, ClassNotFoundException;
     List<Item> movableItem() throws SQLException, ClassNotFoundException;
      boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException;
}
