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
//        ResultSet rst = DbConnection.getInstance()
//                .getConnection().prepareStatement(
//                        "SELECT OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1"
//                ).executeQuery();
//        ResultSet rst = CrudUtil.executeQuery("SELECT OrderID FROM `Order` ORDER BY OrderID DESC LIMIT 1");
//        if (rst.next()){
//
//            int tempId = Integer.
//                    parseInt(rst.getString(1).split("-")[1]);
//            tempId=tempId+1;
//            if (tempId<=9){
//                return "O-00"+tempId;
//            }else if(tempId<99){
//                return "O-0"+tempId;
//            }else{
//                return "O-"+tempId;
//            }
//
//        }else{
//            return "O-001";
//        }

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql="SELECT OrderID FROM Order ORDER BY OrderID DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> rst = query.list();

        transaction.commit();
        session.close();

        if (! rst.isEmpty()){

            int tempId = Integer.
                    parseInt(rst.get(1).split("-")[1]);
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
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public boolean placeOrder(OrderDTO orderDTO) {
//        Connection con=null;
//        try {
//            con= DbConnection.getInstance().getConnection();
//            con.setAutoCommit(false);
//
//            boolean b = CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?)", orderDTO.getOrderID(), orderDTO.getOrderdate(), orderDTO.getCustID(), orderDTO.getCost());
//
//
//            if (b){
//                if (saveOrderDetail(orderDTO.getOrderID(), orderDTO.getItems())){
//                    con.commit();
//                    return true;
//                }else{
//                    con.rollback();
//                    return false;
//                }
//            }else{
//                con.rollback();
//                return false;
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//
//                con.setAutoCommit(true);
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//
//        return false;
//    }
//
//    private boolean saveOrderDetail(String orderId, ArrayList<OrdreDetailsDTO> items) throws SQLException, ClassNotFoundException {
//        for (OrdreDetailsDTO temp : items
//        ) {
//
//            boolean b = CrudUtil.executeUpdate("INSERT INTO `Order Detail` VALUES(?,?,?,?)", orderId, temp.getItemID(), temp.getOrderQTY(), temp.getDiscount());
//            if (b) {
//
//                if (updateQty(temp.getItemID(), temp.getOrderQTY())){
//
//                }else{
//                    return false;
//                }
//
//            } else {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
//
//        return CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=(QtyOnHand-?) WHERE ItemCode=?",qty,itemCode);
//    }

    public List<Order> customerwiseIncome(String customerID) throws SQLException, ClassNotFoundException {

//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE CustId=?", customerID);
//
//        List<Order> ids = new ArrayList<>();
//         while (rst.next()){
//             ids.add(new Order(
//                      rst.getString(1),
//                      rst.getString(2),
//                      rst.getDouble(4)
//                       )
//             );
//
//        }
//         return ids;

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Order WHERE CustID = :customer_id";
        Query query = session.createQuery(hql);
        query.setParameter("customer_id",customerID);
        List<Order> rst = query.list();
        List<Order> ids = new ArrayList<>();

        for (Order c:rst) {
          ids.add( new Order( c.getOrderID(),c.getOrderdate(),c.getCost()));
        }
        transaction.commit();
        session.close();
        return ids ;
    }

    public List<Order> income(String formYear, String toYear, String formMonth, String toMonth, String formday, String toDay) throws SQLException, ClassNotFoundException {
//
//
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM `Order` WHERE OrderDate BETWEEN ? AND ?", formYear + "-" + formMonth + "-" + formday, toYear + "-" + toMonth + "-" + toDay);
//
//        List<Order> ids = new ArrayList<>();
//        while (rst.next()){
//            ids.add(new Order(
//                         rst.getString(1),
//                         rst.getString(2),
//                         rst.getString(3),
//                         rst.getDouble(4)
//                   )
//            );
//
//        }
//        return ids;

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Order WHERE Orderdate BETWEEN :from AND :to";
        Query query = session.createQuery(hql);
        query.setParameter("from",formYear + "-" + formMonth + "-" + formday);
        query.setParameter("to",toYear + "-" + toMonth + "-" + toDay);
        List<Order> rst = query.list();
        List<Order> ids = new ArrayList<>();

        for (Order c:rst) {
            ids.add( new Order( c.getOrderID(),c.getOrderdate(),c.getCustID(),c.getCost()));
        }
        transaction.commit();
        session.close();
        return ids ;
    }

    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
//       return CrudUtil.executeUpdate("INSERT INTO `Order` VALUES(?,?,?,?)", order.getOrderID(), order.getOrderdate(), order.getCustID(), order.getCost());
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
