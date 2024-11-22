package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.*;
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
    private Label priceTag;

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
            // Retrieve selected values from the UI
            String selectedBookId = bookComboTxt.getSelectionModel().getSelectedItem();
            String selectedCourierId = courierComboTxt.getSelectionModel().getSelectedItem();
            String selectedCustomerId = custComboTxt.getSelectionModel().getSelectedItem();
            String selectedDiscountId = discountComboTxt.getSelectionModel().getSelectedItem();

            // Check if selections are made
            if (selectedBookId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a book.").show();
                return;
            }
            if (selectedCourierId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a courier.").show();
                return;
            }
            if (selectedCustomerId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a customer.").show();
                return;
            }
            if (selectedDiscountId == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a discount.").show();
                return;
            }

            // Validate quantity input
            int qty;
            try {
                qty = Integer.parseInt(qtyTxt.getText().trim());
                if (qty <= 0) {
                    new Alert(Alert.AlertType.ERROR, "Quantity must be greater than zero.").show();
                    return;
                }
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Please enter a valid number for quantity.").show();
                return;
            }

            // Check available quantity in stock
            int availableQty = bookModel.getAvailableQuantity(Integer.parseInt(selectedBookId));
            if (qty > availableQty) {
                new Alert(Alert.AlertType.ERROR, "Insufficient stock! Available quantity: " + availableQty).show();
                return;
            }

            // Create OrderDto and OrderDetails objects
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
                    0.0, // Price will be calculated in the model
                    qty
            );

            // Attempt to add the order
            boolean success = orderModel.addOrder(orderDto, orderDetails);
            if (success) {
                // Display the final price after discount
                new Alert(Alert.AlertType.INFORMATION, "Order has been successfully placed! Your Payment is (with Discount): $" + orderDetails.getPrice()).show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while placing the order.").show();
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

        // Add action listeners for combo boxes
        this.custComboTxt.setOnAction(event -> {
            int custID = Integer.parseInt(custComboTxt.getSelectionModel().getSelectedItem());
            try {
                CustomerDto customerDto = customerModel.findById(String.valueOf(custID));
                if (customerDto != null){
                    customerLbl.setText(customerDto.getCustName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.bookComboTxt.setOnAction(event -> {
            int bookID = Integer.parseInt(bookComboTxt.getSelectionModel().getSelectedItem());
            try {
                BookDto bookDto = bookModel.findById(String.valueOf(bookID));
                if (bookDto != null){
                    bookLbl.setText(bookDto.getBookName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.discountComboTxt.setOnAction(event -> {
            int discountID = Integer.parseInt(discountComboTxt.getSelectionModel().getSelectedItem());
            try {
                DiscountDto discountDto = discountModel.findById(String.valueOf(discountID));
                if (discountDto != null){
                    discountLbl.setText(String.valueOf(discountDto.getAmount()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.courierComboTxt.setOnAction(event -> {
            int courierID = Integer.parseInt(courierComboTxt.getSelectionModel().getSelectedItem());
            try {
                CourierDto courierDto = courierModel.findById(String.valueOf(courierID));
                if (courierDto != null){
                    courierLbl.setText(courierDto.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

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