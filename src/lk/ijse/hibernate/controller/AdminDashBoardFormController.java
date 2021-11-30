package lk.ijse.hibernate.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AdminDashBoardFormController {

    public AnchorPane pic1;

    public void RegisterNewItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/RegisterNewItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void ModifyItemDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ModifyItemDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void RemoveItemOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/RemoveItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void MovableItemOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        URL resource = getClass().getResource("../views/MostAndLeastMovableItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));

        MostAndLeastMovableItemFormController m = new MostAndLeastMovableItemFormController();
        m.movable();

    }

    public void customerwiseIncomeOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerWiseIncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void IncomeReportOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/IncomeForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminLoginForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
