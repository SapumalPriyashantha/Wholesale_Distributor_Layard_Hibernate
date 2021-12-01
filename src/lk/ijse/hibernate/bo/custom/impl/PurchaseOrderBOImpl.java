package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.Util.FactoryConfiguration;
import lk.ijse.hibernate.bo.custom.PurchaseOrderBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.dao.custom.OrderDAO;
import lk.ijse.hibernate.dao.custom.OrderDetailsDAO;
//import lk.ijse.hibernate.db.DbConnection;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.dto.OrdreDetailsDTO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

//    private final CustomerDAO customerDAO = new CustomerDAOImpl();
//    private final ItemDAO itemDAO = new ItemDAOImpl();
//    private final OrderDAO orderDAO = new OrderDAOImpl();
//    private final OrderDetailsDAOlmpl orderDetailsDAO = new OrderDetailsDAOlmpl();

    ///////////////////////////            trancsetion
    @Override
    public boolean placeOrder(OrderDTO orderDTO) {
//        Connection con=null;
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

//        session.save(c);


        try {
            Customer customer = session.get(Customer.class,orderDTO.getCustID());

            boolean b = orderDAO.add(new Order(orderDTO.getOrderID(),orderDTO.getOrderdate(),orderDTO.getCost(),customer));

            if (b){
                if (saveOrderDetail(orderDTO.getOrderID(), orderDTO.getItems())){
//                    con.commit();
                    transaction.commit();
                    return true;
                }else{
//                    con.rollback();
                    transaction.rollback();
                    return false;
                }
            }else{
//                con.rollback();
                transaction.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {

            session.close();
//                con.setAutoCommit(true);

        }

        return false;
    }

    @Override
    public boolean saveOrderDetail(String orderId, ArrayList<OrdreDetailsDTO> items) throws SQLException, ClassNotFoundException {
        for (OrdreDetailsDTO temp : items
        ) {

            boolean b = orderDetailsDAO.addOrderDetails(orderId, temp.getItemID(), temp.getOrderQTY(), temp.getDiscount());

            if (b) {

                if (updateQty(temp.getItemID(), temp.getOrderQTY())){

                }else{
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateQty(String itemCode, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(itemCode,qty);
    }

    /////////////////////////////////////////////////////

    @Override
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public ItemDTO getItemData(String itemcode) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getItem(itemcode);
        return new ItemDTO(item.getItemCode(),item.getDescription(),
                item.getPackSize(),item.getUnitPrice(),
                item.getQtyOnHand(),item.getFirstQtyOnHand());
    }

    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderId();
    }

}
