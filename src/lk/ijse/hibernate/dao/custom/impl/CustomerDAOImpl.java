package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.Util.FactoryConfiguration;
//import lk.ijse.hibernate.dao.CrudUtil;
import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer c) throws SQLException, ClassNotFoundException {
//        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?,?,?,?)",
//                 c.getCustID(), c.getCustTitle(), c.getCustName(),
//                 c.getCustAddress(), c.getCity(), c.getProvince(),
//                 c.getPostalCode());

        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.save(c);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();
        List<String> ids = new ArrayList<>();
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        String hql="FROM Customer";
        Query query = session.createQuery(hql);
        List<Customer> rst = query.list();
        for (Customer c:rst) {
            ids.add(c.getCustID());
        }

//        while (rst.next()){
//            ids.add(
//                    rst.getString(1)
//            );
//        }

        transaction.commit();
        session.close();
        return ids;

    }

    @Override
    public Customer getCustomer(String customerID) throws SQLException, ClassNotFoundException {
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer WHERE CustId=?", customerID);
//        if (rst.next()) {
//            return new Customer(
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getString(4),
//                    rst.getString(5),
//                    rst.getString(6),
//                    rst.getString(7)
//            );
//
//        } else {
//            return null;
//        }
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Customer WHERE CustID = : customer_id";
        Query query = session.createQuery(hql);
        query.setParameter("customer_id",customerID);
        List<Customer> rst = query.list();
        Customer c1 = null;
        for (Customer c:rst) {
            c1= new Customer( c.getCustTitle(),c.getCustName(),c.getCustAddress(),c.getCity(),c.getProvince(),c.getPostalCode());
        }
        transaction.commit();
        session.close();
        return c1 ;
    }




}
