package lk.ijse.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    private String CustID;
    private  String CustTitle;
    private String CustName;
    private String CustAddress;
    private String City;
    private String Province;
    private String PostalCode;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List <Order> order= new ArrayList<>();

    public Customer() {
    }

    public Customer(String custTitle, String custName, String custAddress, String city, String province, String postalCode) {
        CustTitle = custTitle;
        CustName = custName;
        CustAddress = custAddress;
        City = city;
        Province = province;
        PostalCode = postalCode;
    }

    public Customer(String custID, String custTitle, String custName, String custAddress, String city, String province, String postalCode) {
        CustID = custID;
        CustTitle = custTitle;
        CustName = custName;
        CustAddress = custAddress;
        City = city;
        Province = province;
        PostalCode = postalCode;
    }

    public Customer(String custID, String custTitle, String custName, String custAddress, String city, String province, String postalCode, List<Order> order) {
        CustID = custID;
        CustTitle = custTitle;
        CustName = custName;
        CustAddress = custAddress;
        City = city;
        Province = province;
        PostalCode = postalCode;
        this.order = order;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public String getCustTitle() {
        return CustTitle;
    }

    public void setCustTitle(String custTitle) {
        CustTitle = custTitle;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustAddress() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        CustAddress = custAddress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustID='" + CustID + '\'' +
                ", CustTitle='" + CustTitle + '\'' +
                ", CustName='" + CustName + '\'' +
                ", CustAddress='" + CustAddress + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", PostalCode='" + PostalCode + '\'' +
                ", order=" + order +
                '}';
    }
}
