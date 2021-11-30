package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.bo.BoFactory;
import lk.ijse.hibernate.bo.custom.CustomerBO;
import lk.ijse.hibernate.bo.custom.OrderBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.OrderDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class CustomerWiseIncomeFormController {
    public AnchorPane pic1;
    public ComboBox cmbCustomerId;
    public TextField lblcustTitle;
    public TextField lblcustName;
    public TextField lblCity;
    public TextField lblcustAddress;
    public TextField lblProvince;
    public TextField lblPostalCode;
    public TableView<OrderDTO> tblCustomerwiseIncome;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colIncome;
    public Label lblTotal;

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);;
    private final OrderBO orderBO = (OrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDER);

    static ObservableList<OrderDTO> CustomerWise= FXCollections.observableArrayList();

    public void initialize(){

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("cost"));


        tblCustomerwiseIncome.setItems(CustomerWise);


        try {
            loadCustomerIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerDta((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        CustomerWise.clear();


    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {

        List<String> customerIds = customerBO
                .getAllCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);

    }

    private void setCustomerDta(String customerID) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = customerBO.getCustomer(customerID);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            lblcustTitle.setText(c1.getCustTitle());
            lblcustName.setText(c1.getCustName());
            lblcustAddress.setText(c1.getCustAddress());
            lblCity.setText(c1.getCity());
            lblProvince.setText(c1.getProvince());
            lblPostalCode.setText(c1.getPostalCode());

            customerWiseIncome(customerID);

        }
    }

    public  void customerWiseIncome(String customerID) throws SQLException, ClassNotFoundException {

        List<OrderDTO> orderDTOS = orderBO.customerwiseIncome(customerID);
        CustomerWise.clear();
        for (OrderDTO orderDTO : orderDTOS) {
            CustomerWise.add(new OrderDTO(orderDTO.getOrderID(), orderDTO.getOrderdate(),orderDTO.getCost()));
            calculateCost();
        }
    }

    void calculateCost(){
        double total=0;
        for (OrderDTO o1:CustomerWise
        ) {
            total+=o1.getCost();
        }
        lblTotal.setText(total+" /=");

    }


    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
