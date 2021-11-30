package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.Util.ValidationUtil;
import lk.ijse.hibernate.bo.BoFactory;
import lk.ijse.hibernate.bo.custom.CustomerBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class AddNewCustomerDetailsFormController {

    public AnchorPane pic1;
    public TextField txtCustID;
    public TextField txtCustTitle;
    public TextField txtCustName;
    public TextField txtCustAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtPostalCode;
    public Button btnconform;

    private final CustomerBO customerBO = (CustomerBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.CUSTOMER);

    public void initialize(){
        btnconform.setDisable(true);
        storeValidations();
    }

    //validation
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern idPattern = Pattern.compile("^(c)[0-9]{1,3}$");
    Pattern titlePattern = Pattern.compile("^[A-z]{2,3}$");
    Pattern namePattern = Pattern.compile("^[A-z ]{3,20}$");
    Pattern addressPattern = Pattern.compile("^[A-z0-9/ ]{5,20}$");
    Pattern cityPattern = Pattern.compile("^[A-z]{2,10}$");
    Pattern provincePattern = Pattern.compile("^[A-z]{2,10}$");
    Pattern postalcodePattern = Pattern.compile("^[0-9]{2,10}$");

    private void storeValidations() {
        map.put(txtCustID, idPattern);
        map.put(txtCustTitle, titlePattern);
        map.put(txtCustName, namePattern);
        map.put(txtCustAddress, addressPattern);
        map.put(txtCity, cityPattern);
        map.put(txtProvince, provincePattern);
        map.put(txtPostalCode, postalcodePattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnconform);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Supplier Button").showAndWait();
            }
        }
    }

  //save customer data in server
    public void saveNewCustomerDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO c1 = new CustomerDTO(
                txtCustID.getText(),txtCustTitle.getText(),
                txtCustName.getText(),txtCustAddress.getText(),
                txtCity.getText(),txtProvince.getText(),txtPostalCode.getText()

        );

        if(customerBO.addCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    //back
    public void backOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }


}
