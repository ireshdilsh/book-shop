package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CourierDto;
import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.dto.DiscountDto;
import com.example.bookshop.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
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

    // lables
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

        // ------------------------------------------------------//
        this.custComboTxt.setOnAction(event -> {
            String selectedCustomerId = custComboTxt.getSelectionModel().getSelectedItem();
            CustomerDto dto = null;
            try {
                dto = customerModel.findById(selectedCustomerId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // If customer found (customerDTO not null)
            if (dto != null) {
                customerLbl.setText(dto.getCustName());
            }
        });

        // ------------------------------------------------------//
        this.bookComboTxt.setOnAction(event -> {
            String selectedBookId = bookComboTxt.getSelectionModel().getSelectedItem();
            BookDto dto = null;
            try {
                dto = bookModel.findById(selectedBookId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // If customer found (customerDTO not null)
            if (dto != null) {
                bookLbl.setText(dto.getBookName());
            }
        });
        // ------------------------------------------------------//

        this.discountComboTxt.setOnAction(event -> {
            String selectedDiscountId = discountComboTxt.getSelectionModel().getSelectedItem();
            DiscountDto dto = null;
            try {
                dto = discountModel.findById(selectedDiscountId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // If customer found (customerDTO not null)
            if (dto != null) {
                discountLbl.setText(String.valueOf(dto.getAmount()));
            }
        });
        // ------------------------------------------------------//
        this.courierComboTxt.setOnAction(event -> {
            String selectedCourierId = courierComboTxt.getSelectionModel().getSelectedItem();
            CourierDto dto = null;
            try {
                dto = courierModel.findById(selectedCourierId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            // If customer found (customerDTO not null)
            if (dto != null) {
                courierLbl.setText(dto.getName());
            }
        });
        // ------------------------------------------------------//


        try {
          getAllDiscounts();
          getAllCustomers();
          getAllCourier();
          getAllBook();
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

    private void getAllBook() throws SQLException, ClassNotFoundException {
        ArrayList<String> book = orderModel.getAllBook();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(book);
        bookComboTxt.setItems(observableList);
    }

    private void getAllDiscounts() throws SQLException, ClassNotFoundException {
        ArrayList<String> discount = orderModel.getAllDiscounts();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(discount);
        discountComboTxt.setItems(observableList);
    }
}
