package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order,String> {
      String getOrderId() throws SQLException, ClassNotFoundException;
     List<Order> customerwiseIncome(String customerID) throws SQLException, ClassNotFoundException;
     List<Order> income(String formYear, String toYear, String formMonth, String toMonth, String formday, String toDay) throws SQLException, ClassNotFoundException;

}
