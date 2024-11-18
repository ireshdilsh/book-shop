package com.example.bookshop.controller.modelsController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RiviewController {
      @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField riviewCustNameTxt;

    @FXML
    private TextArea riviewDiscriptionTxt;

    @FXML
    void addNewRiview(ActionEvent event) {

    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

}
