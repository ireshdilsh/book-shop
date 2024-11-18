package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EmployeeController {

        @FXML
    private TextField ageTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField conactTxt;

    @FXML
    private TextField employeeNameTxt;

    @FXML
    private ComboBox<?> promotionComboTxt;

    @FXML
    private ComboBox<?> salaryComboTxt;

    @FXML
    void addNewEmployee(ActionEvent event) {

    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }
}
