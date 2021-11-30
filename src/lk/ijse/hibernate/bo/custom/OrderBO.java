package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {

    List<OrderDTO> income(String formYear, String toYear, String formMonth, String toMonth, String formday, String toDay)throws SQLException, ClassNotFoundException;

    List<OrderDTO> customerwiseIncome(String customerID)throws SQLException, ClassNotFoundException;
}
