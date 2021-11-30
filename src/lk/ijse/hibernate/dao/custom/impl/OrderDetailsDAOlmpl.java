package lk.ijse.hibernate.dao.custom.impl;

//import lk.ijse.hibernate.dao.CrudUtil;
import lk.ijse.hibernate.Util.FactoryConfiguration;
import lk.ijse.hibernate.dao.custom.OrderDetailsDAO;
import lk.ijse.hibernate.dto.OrdreDetailsDTO;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.Order;
import lk.ijse.hibernate.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Order order = session.get(Order.class, "orderId");
        Item item = session.get(Item.class, "ItemID");

        OrderDetails orderDetails = new OrderDetails(order, item, OrderQTY, Discount);
        session.save(orderDetails);
        transaction.commit();
        session.close();
        return true;
    }
}
