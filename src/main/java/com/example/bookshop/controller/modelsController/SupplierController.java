package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SupplierController {
    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField supplierContactTxt;

    @FXML
    private TextField supplierNameTxt;

    @FXML
    void addNewSupplier(ActionEvent event) {

    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

}
