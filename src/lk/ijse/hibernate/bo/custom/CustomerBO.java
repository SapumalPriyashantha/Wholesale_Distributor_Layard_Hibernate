package lk.ijse.hibernate.bo.custom;

import lk.ijse.hibernate.bo.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean addCustomer(CustomerDTO c1) throws SQLException, ClassNotFoundException;

    List<String> getAllCustomerIds()throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomer(String customerID)throws SQLException, ClassNotFoundException;
}
