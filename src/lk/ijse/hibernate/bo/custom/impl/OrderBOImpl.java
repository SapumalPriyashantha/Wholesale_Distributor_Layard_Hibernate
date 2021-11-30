package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.OrderBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.OrderDAO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public List<OrderDTO> income(String formYear, String toYear, String formMonth, String toMonth, String formday, String toDay) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> incomeData=new ArrayList<>();
        List<Order> income = orderDAO.income(formYear, toYear, formMonth, toMonth, formday, toDay);
        for (Order order : income) {
            incomeData.add(new OrderDTO(order.getOrderID(),order.getOrderdate(),
                    order.getCustID(),order.getCost()));
        }
        return incomeData;
    }

    @Override
    public List<OrderDTO> customerwiseIncome(String customerID)throws SQLException, ClassNotFoundException {
        ArrayList<OrderDTO> customerwiseIncomedata=new ArrayList<>();
        List<Order> orders = orderDAO.customerwiseIncome(customerID);
        for (Order order : orders) {
            customerwiseIncomedata.add(new OrderDTO(order.getOrderID(),order.getOrderdate(),
                    order.getCustID(),order.getCost()));
        }
        return customerwiseIncomedata;
    }
}
