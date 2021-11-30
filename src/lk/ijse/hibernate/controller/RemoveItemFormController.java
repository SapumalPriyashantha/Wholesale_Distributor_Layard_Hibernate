package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.bo.BoFactory;
import lk.ijse.hibernate.bo.custom.ItemBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.dto.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class RemoveItemFormController {

    public TextField txtItemCode;
    public TextField txtDescription;
    public TextField txtPacksize;
    public TextField txtunitPrice;
    public TextField txtQtyOnHand;
    public AnchorPane pic1;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    public void searchItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemcode = txtItemCode.getText();


        ItemDTO c1= itemBO.getItem(itemcode);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }
    }

    void setData(ItemDTO c1){
        txtItemCode.setText(c1.getItemCode());
        txtDescription.setText(c1.getDescription());
        txtPacksize.setText(c1.getPackSize());
        txtunitPrice.setText(String.valueOf(c1.getUnitPrice()));
        txtQtyOnHand.setText(String.valueOf(c1.getQtyOnHand()));
    }

    public void ItemDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (itemBO.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
