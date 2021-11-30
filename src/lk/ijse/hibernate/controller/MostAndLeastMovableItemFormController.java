package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.bo.BoFactory;
import lk.ijse.hibernate.bo.custom.ItemBO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.views.tm.ItemTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class MostAndLeastMovableItemFormController {

    public AnchorPane pic1;
    public TableView<ItemTm> tblMovableItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitePrice;
    public TableColumn colQtyOnHand;
    public TableColumn colFirstQtyOnHand;
    public TableColumn colMovable;

    private final ItemBO itemBO = (ItemBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.ITEM);

    static ObservableList<ItemTm> MovableItem= FXCollections.observableArrayList();

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colFirstQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("FirstQtyOnHand"));
        colMovable.setCellValueFactory(new PropertyValueFactory<>("Movable"));

        tblMovableItem.setItems(MovableItem);

    }
    //String[] args
    public  void movable() throws SQLException, ClassNotFoundException {
        List<ItemDTO> itemDTOS = itemBO.movableItem();
        for (ItemDTO itemData : itemDTOS) {
            double Movable=100-(((double)itemData.getQtyOnHand() / (double)itemData.getFirstQtyOnHand())*100);
            ItemTm itemtm=new ItemTm(itemData.getItemCode(),
                    itemData.getDescription(),
                    itemData.getPackSize(),
                    itemData.getUnitPrice(),
                    itemData.getQtyOnHand(),
                    itemData.getFirstQtyOnHand(),
                    Movable);
            MovableItem.add(itemtm);
        }

    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }



}
