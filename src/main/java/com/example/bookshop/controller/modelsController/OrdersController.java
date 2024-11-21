package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.OrderDetails;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    private final OrderModel orderModel = new OrderModel();

    @FXML
    private ComboBox<String> bookComboTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private ComboBox<String> courierComboTxt;

    @FXML
    private ComboBox<String> custComboTxt;

    @FXML
    private Label dateTxt;

    @FXML
    private ComboBox<String> discountComboTxt;

    @FXML
    private TextField qtyTxt;

    // Labels
    @FXML
    private Label bookLbl;
    @FXML
    private Label courierLbl;
    @FXML
    private Label customerLbl;
    @FXML
    private Label discountLbl;

    @FXML
    void addNewOrder(ActionEvent event) {
        try {
            String selectedBookId = bookComboTxt.getSelectionModel().getSelectedItem();
            String selectedCourierId = courierComboTxt.getSelectionModel().getSelectedItem();
            String selectedCustomerId = custComboTxt.getSelectionModel().getSelectedItem();
            String selectedDiscountId = discountComboTxt.getSelectionModel().getSelectedItem();
            int qty = Integer.parseInt(qtyTxt.getText());

            OrderDto orderDto = new OrderDto(
                    Date.valueOf(LocalDate.now()),
                    Integer.parseInt(selectedCustomerId),
                    Integer.parseInt(selectedBookId),
                    Integer.parseInt(selectedCourierId),
                    Integer.parseInt(selectedDiscountId)
            );

            OrderDetails orderDetails = new OrderDetails(
                    Integer.parseInt(selectedBookId),
                    0, // Placeholder for order ID, to be set by the model
                    0.0, // You would calculate the price here if needed
                    qty
            );

            boolean success = orderModel.addOrder(orderDto, orderDetails);
            if (success) {
                new Alert(Alert.AlertType.INFORMATION, "Order has been successfully placed!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

    private final CustomerModel customerModel = new CustomerModel();
    private final BookModel bookModel = new BookModel();
    private final CourierModel courierModel = new CourierModel();
    private final DiscountModel discountModel = new DiscountModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dateTxt.setText(LocalDate.now().toString());

        try {
            getAllDiscounts();
            getAllCustomers();
            getAllCourier();
            getAllBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<String> customers = orderModel.getAllCustomers();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customers);
        custComboTxt.setItems(observableList);
    }

    private void getAllCourier() throws SQLException, ClassNotFoundException {
        ArrayList<String> courier = orderModel.getAllCourier();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(courier);
        courierComboTxt.setItems(observableList);
    }

    private void getAllBooks() throws SQLException, ClassNotFoundException {
        ArrayList<String> books = orderModel.getAllBooks();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(books);
        bookComboTxt.setItems(observableList);
    }

    private void getAllDiscounts() throws SQLException, ClassNotFoundException {
        ArrayList<String> discounts = orderModel.getAllDiscounts();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(discounts);
        discountComboTxt.setItems(observableList);
    }

    public void clearFields() {
        discountComboTxt.getItems().clear();
        custComboTxt.getItems().clear();
        courierComboTxt.getItems().clear();
        bookComboTxt.getItems().clear();
        qtyTxt.setText("");
        discountLbl.setText("");
        customerLbl.setText("");
        courierLbl.setText("");
        bookLbl.setText("");
    }
}
