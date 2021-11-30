package lk.ijse.hibernate.controller;

import lk.ijse.hibernate.bo.BoFactory;
import lk.ijse.hibernate.bo.custom.PurchaseOrderBO;
import lk.ijse.hibernate.dao.custom.impl.OrderDAOImpl;
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
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.dto.OrdreDetailsDTO;
import lk.ijse.hibernate.views.tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderFormController {

    public AnchorPane pic1;
    public ComboBox cmbCustomerID;
    public ComboBox cmbItemID;
    public Label lblDate;
    public Label lblOrderID;
    public TextField txtDescription;
    public TextField txtPckSize;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TextField txtQtyOnHand;
    public Button btnAddToCart;
    public TableView<CartTm> tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitePrice;
    public TableColumn colQty;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public Label lblTotalAmount;
    public Label lblTotalDiscount;
    public Button btnComformOfOrder;
    public TextField txtAmountPaid;
    public Label lblRemaining;
    int cartSelectedRowForRemove = -1;

    private final PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BoFactory.getBOFactory().getBO(BoFactory.BoTypes.PURCHASE_ORDER);

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDate();
        setOrderID();

        try {

            loadCustomerIds();
            loadItemIds();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItemID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });

        btnComformOfOrder.setVisible(false);

    }
    //--------------------------------------------------------------------------------------------------------------

    private void loadDate(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));
    }

    private void setOrderID() {
        try {

            lblOrderID.setText(purchaseOrderBO.getOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
         List<String> itemIds =purchaseOrderBO.getAllItemIds();
        cmbItemID.getItems().addAll(itemIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
          List<String> customerIds = purchaseOrderBO.getAllCustomerIds();
        cmbCustomerID.getItems().addAll(customerIds);

    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        ItemDTO i1 = purchaseOrderBO.getItemData(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtDescription.setText(i1.getDescription());
            txtPckSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        }
    }

    ObservableList<CartTm> obList= FXCollections.observableArrayList();
    public void AddToCartOnAction(ActionEvent actionEvent) {

        String itemCode = (String) cmbItemID.getValue();
        String description = txtDescription.getText();
        String packSize = txtPckSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double unitDiscount;



        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

        if(itemCode.equals("i1")){
            unitDiscount=unitPrice/20;
        }else if(itemCode.equals("i2")){
            unitDiscount=unitPrice/50;
        }else if(itemCode.equals("i3")){
            unitDiscount=unitPrice/40;
        }else {
            unitDiscount=unitPrice/30;
        }


        double discount = unitDiscount * qty;

        double total = (unitPrice*qty)-(discount) ;

        if (qtyOnHand<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                itemCode,
                description,
                packSize,
                unitPrice,
                qty,
                discount,
                total
        );

        int rowNumber=isExists(tm);

        if (rowNumber==-1){// new Add
            obList.add(tm);
        }else{
            // update
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getPackSize(),
                        temp.getUnitPrice(),
                    temp.getQty()+qty,
                    temp.getDiscount()+discount,
                    temp.getTotal()+total
            );

            if (qtyOnHand<(temp.getQty()+qty)){
                new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
                return;
            }

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblCart.setItems(obList);
        btnComformOfOrder.setVisible(true);
        calculateCost();

    }

    private int isExists(CartTm tm){
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }

    void calculateCost(){
        double total=0;
        for (CartTm tm:obList
        ) {
            total+=tm.getTotal();
        }
        lblTotalAmount.setText(total+" /=");

        double discount=0;
        for (CartTm tm:obList
        ) {
            discount+=tm.getDiscount();
        }
        lblTotalDiscount.setText(discount+" /=");


    }

    public void RemoveItemOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblCart.refresh();
        }
    }

    public void comformOfOrder(ActionEvent actionEvent) {
        ArrayList<OrdreDetailsDTO> items= new ArrayList<>();
        double total=0;
        for (CartTm tempTm:obList
        ) {
            total+=tempTm.getTotal();
            items.add(new OrdreDetailsDTO(lblOrderID.getText(),tempTm.getItemCode(),
                    tempTm.getQty(),tempTm.getDiscount()));
        }

        double amountpaid=Double.parseDouble(txtAmountPaid.getText());
        double remaining=amountpaid-total;
        lblRemaining.setText(remaining+" /=");

        OrderDTO orderDTO = new OrderDTO(lblOrderID.getText(),
                lblDate.getText(),
                (String) cmbCustomerID.getValue(), total,
                items

        );


        if (purchaseOrderBO.placeOrder(orderDTO)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderID();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }


    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CashierDashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) pic1.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
