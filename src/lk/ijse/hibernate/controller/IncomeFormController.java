package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.bo.BoFactory;
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
import lk.ijse.hibernate.dto.OrderDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class IncomeFormController {
    public AnchorPane pic1;
    public Button btnSearch;
    public TextField txtFormYear;
    public TextField txtToYear;
    public TextField txtFormMonth;
    public TextField txtToMonth;
    public TextField txtFormday;
    public TextField txtToDay;
    public TableView tblIncomereport;
    public TableColumn colOrderId;
    public TableColumn colOrderdate;
    public TableColumn colCustomerId;
    public TableColumn colIncome;
    public Label lblTotal;

    private final OrderBO orderBO = (OrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ORDER);

    static ObservableList<OrderDTO> IncomeReport= FXCollections.observableArrayList();


    public void initialize() {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        colOrderdate.setCellValueFactory(new PropertyValueFactory<>("Orderdate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("cost"));


        tblIncomereport.setItems(IncomeReport);

    }

    public void searchIncomeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String FormYear = txtFormYear.getText();
        String ToYear = txtToYear.getText();
        String FormMonth = txtFormMonth.getText();
        String ToMonth = txtToMonth.getText();
        String Formday = txtFormday.getText();
        String ToDay = txtToDay.getText();

        List<OrderDTO> income = orderBO.income(FormYear, ToYear, FormMonth, ToMonth, Formday, ToDay);
        IncomeReport.clear();

        for (OrderDTO incomeDetails : income) {
            IncomeReport.add(new OrderDTO(incomeDetails.getOrderID(),incomeDetails.getOrderdate(),incomeDetails.getCustID(),incomeDetails.getCost()));
            calculateCost();
        }
    }

    void calculateCost(){
        double total=0;
        for (OrderDTO o1:IncomeReport
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
