package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CustomerController {
      @FXML
    private TextField addressTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField conactTxt;

    @FXML
    private TextField custNameTxt;

    public void addNewCustomer(ActionEvent actionEvent){
        System.out.println("lsjdfjklsdf");
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }
}
