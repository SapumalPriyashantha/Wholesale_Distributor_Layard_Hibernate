package lk.ijse.hibernate.bo.custom.impl;

import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO c1) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(c1.getCustID(),c1.getCustTitle(),
                c1.getCustName(),c1.getCustAddress(),
                c1.getCity(),c1.getProvince(),c1.getPostalCode()));
    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public CustomerDTO getCustomer(String customerID) throws SQLException, ClassNotFoundException {
        Customer c1 = customerDAO.getCustomer(customerID);
        return new CustomerDTO(c1.getCustID(),c1.getCustTitle(),
                c1.getCustName(),c1.getCustAddress(),
                c1.getCity(),c1.getProvince(),c1.getPostalCode());
    }
}
