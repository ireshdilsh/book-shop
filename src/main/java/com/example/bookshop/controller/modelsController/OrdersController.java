package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OrdersController {
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

}
