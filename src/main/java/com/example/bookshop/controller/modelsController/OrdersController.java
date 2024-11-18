package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {
     @FXML
    private ComboBox<?> bookComboTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private ComboBox<?> courierComboTxt;

    @FXML
    private ComboBox<?> custComboTxt;

    @FXML
    private Label dateTxt;

    @FXML
    private ComboBox<?> discountComboTxt;

    @FXML
    void addNewOrder(ActionEvent event) {

    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      try {
          getAllDiscounts();
          getAllCustomers();
          getAllCourier();
          getAllBook();
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    private void getAllCustomers() {
    }

    private void getAllCourier() {
    }

    private void getAllBook() {
    }

    private void getAllDiscounts() {
    }
}
