package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.Util.ValidationUtil;
import lk.ijse.hibernate.bo.BoFactory;
import lk.ijse.hibernate.bo.custom.ItemBO;
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
import lk.ijse.hibernate.dto.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegisterNewItemFormController {

    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtPacksize;
    public TextField txtunitPrice;
    public TextField txtQtyOnHand;
    public Button btnItemconform;
    public AnchorPane pic1;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void initialize(){
        btnItemconform.setDisable(true);
        storeValidations();
    }

    //validation
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    Pattern codePattern = Pattern.compile("^(i)[0-9]{1,3}$");
    Pattern descriptionPattern = Pattern.compile("^[A-z]{2,10}$");
    Pattern packsizePattern = Pattern.compile("^[0-9]{1,5}$");
    Pattern uniteprizePattern = Pattern.compile("^[0-9]{1,5}$");
    Pattern QTyonhandPattern = Pattern.compile("^[0-9]{1,5}$");

    private void storeValidations() {
        map.put(txtItemCode, codePattern);
        map.put(txtDescription, descriptionPattern);
        map.put(txtPacksize, packsizePattern);
        map.put(txtunitPrice, uniteprizePattern);
        map.put(txtQtyOnHand, QTyonhandPattern);
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnItemconform);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Please Enter Add New Supplier Button").showAndWait();
            }
        }
    }

    // save Item data in saver
    public void ItemConformOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemDTO c1 = new ItemDTO(
                txtItemCode.getText(),txtDescription.getText(),
                txtPacksize.getText(),
                Double.parseDouble(txtunitPrice.getText()), Integer.parseInt(txtQtyOnHand.getText()),Integer.parseInt(txtQtyOnHand.getText())
        );

        if(itemBO.addItem(c1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }

    }
    // back
    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
