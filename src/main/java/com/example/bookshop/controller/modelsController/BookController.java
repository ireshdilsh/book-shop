package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookController {
     @FXML
    private TextField bookNameTxt;

    @FXML
    private ComboBox<?> categoryDropdownTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField priceTxt;

    @FXML
    void addNewBook(ActionEvent event) {
        System.out.println("Add new Book Button is Working...");
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }
}
