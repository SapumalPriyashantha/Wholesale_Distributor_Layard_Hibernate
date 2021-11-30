package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.Util.FactoryConfiguration;
//import lk.ijse.hibernate.dao.CrudUtil;
import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item c) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM Item WHERE ItemCode = :Item_code";
        Query query = session.createQuery(hql);
        query.setParameter("Item_code",s);
        if (query.executeUpdate()>0) {
            transaction.commit();
            session.close();
            return true;
        }else {
            transaction.commit();
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Item c) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE Item SET Description=:discription, PackSize= :packsize, UnitPrice=:unitePrice, QtyOnHand=:QtynHand, FirstQtyOnHand=:firstQtyOnHand WHERE ItemCode=:itemCode";
        Query query = session.createQuery(hql);
        query.setParameter("discription",c.getDescription());
        query.setParameter("packsize",c.getPackSize());
        query.setParameter("unitePrice",c.getUnitPrice());
        query.setParameter("QtynHand",c.getQtyOnHand());
        query.setParameter("firstQtyOnHand",c.getFirstQtyOnHand());
        query.setParameter("itemCode",c.getItemCode());

        if (query.executeUpdate()>0) {
            transaction.commit();
            session.close();
            return true;
        }else {
            transaction.commit();
            session.close();
            return false;
        }
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not Supported Yet");
    }

    @Override
    public List<String> getAll() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<String> ids = new ArrayList<>();
        String hql="FROM Item";
        Query query = session.createQuery(hql);
        List<Item> rst = query.list();
        for (Item i:rst) {
            ids.add(i.getItemCode());
        }
        transaction.commit();
        session.close();
        return ids;
    }

    @Override
    public Item getItem(String itemcode) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Item WHERE ItemCode = : item_code";
        Query query = session.createQuery(hql);
        query.setParameter("item_code",itemcode);
        List<Item> rst = query.list();
        Item i1 = null;
        for (Item i:rst) {
            i1= new Item( i.getItemCode(),i.getDescription(),i.getPackSize(),
                    i.getUnitPrice(),i.getQtyOnHand(),i.getFirstQtyOnHand());
        }
        transaction.commit();
        session.close();
        return i1 ;

    }

    @Override
    public List<Item> movableItem() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Item> ids = new ArrayList<>();
        String hql="FROM Item";
        Query query = session.createQuery(hql);
        List<Item> rst = query.list();
        for (Item i:rst) {
            ids.add(new Item( i.getItemCode(),i.getDescription(),i.getPackSize(),
                    i.getUnitPrice(),i.getQtyOnHand(),i.getFirstQtyOnHand()));
        }
        transaction.commit();
        session.close();
        return ids;

    }

    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "UPDATE Item SET QtyOnHand=(QtyOnHand- :qtyOnhand) WHERE ItemCode=:item_code";
        Query query = session.createQuery(hql);
        query.setParameter("qtyOnhand",qty);
        query.setParameter("item_code",itemCode);

        if (query.executeUpdate()>0) {
            transaction.commit();
            session.close();
            return true;
        }else {
            transaction.commit();
            session.close();
            return false;
        }
    }
}
