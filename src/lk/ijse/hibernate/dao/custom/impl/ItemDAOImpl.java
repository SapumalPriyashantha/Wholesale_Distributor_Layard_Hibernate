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
//        return CrudUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?,?,?)",
//                 c.getItemCode(), c.getDescription(), c.getPackSize(),
//                 c.getUnitPrice(), c.getQtyOnHand(), c.getFirstQtyOnHand());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(c);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
//        if(CrudUtil.executeUpdate("DELETE FROM Item WHERE ItemCode=?",s)){
//            return true;
//        }else{
//            return false;
//        }
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
//        return CrudUtil.executeUpdate("UPDATE Item SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=?, FirstQtyOnHand=? WHERE ItemCode=?",
//                c.getDescription(),c.getPackSize(),c.getUnitPrice(),
//                c.getQtyOnHand(),c.getFirstQtyOnHand(),c.getItemCode());

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
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
//        List<String> ids= new ArrayList<>();
//        while (rst.next()){
//            ids.add(
//                    rst.getString(1)
//            );
//        }
//        return ids;
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
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item WHERE ItemCode=?", itemcode);
//        if(rst.next()){
//            return new Item(rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getDouble(4),
//                    rst.getInt(5),
//                    rst.getInt(6)
//            );
//        }else {
//            return null;
//        }

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
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Item");
//        List<Item> ids = new ArrayList<>();
//        while (rst.next()) {
//            ids.add(
//                    new Item(
//                         rst.getString(1),
//                         rst.getString(2),
//                         rst.getString(3),
//                         rst.getDouble(4),
//                         rst.getInt(5),
//                         rst.getInt(6)
//                    )
//            );
//        }
//        return ids;

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
//        return CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=(QtyOnHand-?) WHERE ItemCode=?",qty,itemCode);

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
