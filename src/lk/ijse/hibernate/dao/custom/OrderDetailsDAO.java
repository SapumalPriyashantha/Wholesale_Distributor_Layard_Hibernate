package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.dto.OrdreDetailsDTO;
import lk.ijse.hibernate.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
     boolean addOrderDetails(String orderId, String ItemID, int OrderQTY, double Discount) throws SQLException, ClassNotFoundException;
}
