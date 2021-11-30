package lk.ijse.hibernate.dao.custom.impl;

//import lk.ijse.hibernate.dao.CrudUtil;
import lk.ijse.hibernate.dao.custom.OrderDetailsDAO;
import lk.ijse.hibernate.dto.OrdreDetailsDTO;
import lk.ijse.hibernate.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOlmpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetails ordreDetails) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(OrderDetails ordreDetails) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean addOrderDetails(String orderId, String ItemID, int OrderQTY, double Discount) throws SQLException, ClassNotFoundException {
//        return CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)", orderId, ItemID, OrderQTY, Discount);
        return true;
    }
}
