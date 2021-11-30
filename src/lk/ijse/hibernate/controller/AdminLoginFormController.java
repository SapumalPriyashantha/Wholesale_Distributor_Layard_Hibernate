package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminLoginFormController {

    public AnchorPane pic1;
    public TextField txtUsername;
    public PasswordField txtPassword;

    public void LoginComformOnAction(ActionEvent actionEvent) throws IOException {
        String username ="Admin";
        String password ="Admin";
        if(username.equals(txtUsername.getText()) && password.equals(txtPassword.getText())){
            URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
            Parent load = FXMLLoader.load(resource);
            Stage window = (Stage) pic1.getScene().getWindow();
            window.setScene(new Scene(load));
        }else {
            new Alert(Alert.AlertType.WARNING, "Your Username or Password is incorrect, please enter correct Username or Password").show();
        }

    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
