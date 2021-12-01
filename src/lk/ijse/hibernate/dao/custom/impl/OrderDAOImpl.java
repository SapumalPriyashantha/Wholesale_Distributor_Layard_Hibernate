package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.Util.FactoryConfiguration;
//import lk.ijse.hibernate.dao.CrudUtil;
import lk.ijse.hibernate.dao.custom.OrderDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public String getOrderId() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT OrderID FROM Orders ORDER BY OrderID DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> rst = query.list();

        transaction.commit();
        session.close();

        if (! rst.isEmpty()){

            int tempId = Integer.
                    parseInt(rst.get(0).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "O-00"+tempId;
            }else if(tempId<99){
                return "O-0"+tempId;
            }else{
                return "O-"+tempId;
            }

        }else{
            return "O-001";
        }


    }

    public List<Order> customerwiseIncome(String customerID) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Orders WHERE customer.CustID = :customer_id";
        Query query = session.createQuery(hql);
        query.setParameter("customer_id",customerID);
        List<Order> rst = query.list();

        List<Order> ids = new ArrayList<>();

        for (Order c:rst) {
          ids.add( new Order( c.getOrderID(),c.getOrderdate(),c.getCost(),c.getCustomer()));
        }
        transaction.commit();
        session.close();
        return ids ;
    }

    public List<Order> income(String formYear, String toYear, String formMonth, String toMonth, String formday, String toDay) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Orders WHERE Orderdate BETWEEN :from AND :to";
        Query query = session.createQuery(hql);
        String f = (formYear + "-" + formMonth + "-" + formday);
        String t = (toYear + "-" + toMonth + "-" + toDay);
        query.setParameter("from",f);
        query.setParameter("to",t);

        List<Order> rst = query.list();
        List<Order> ids = new ArrayList<>();

        for (Order c:rst) {
            ids.add( new Order( c.getOrderID(),c.getOrderdate(),c.getCost(),c.getCustomer()));

        }
        transaction.commit();
        session.close();
        return ids ;
   }

    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(order);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getAll() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }
}
