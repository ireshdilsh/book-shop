package com.example.bookshop.controller;

import com.example.bookshop.utils.WindowUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {

    @FXML
    private AnchorPane passwordMainPane;

    @FXML
    private ImageView closeButton;

    @FXML
    private TextField emailText;

    @FXML
    private TextField confirmationCodeText;

    @FXML
    private Label errorLbl;

    @FXML
    private Label confirmationErorLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // close password change windows.
        this.closeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) this.closeButton.getScene().getWindow();
            stage.close();
        });
    }

    public void gotoPasswordChangeViewTwo(ActionEvent actionEvent) {
       new WindowUtil().navigateTo(passwordMainPane,"ForgetPasswordViewTwo");
    }

    public void gotoForgetPasswordViewThree(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(passwordMainPane,"ForgetPasswordViewThree");
    }

    public void gotoForgetPasswordViewFour(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(passwordMainPane,"ForgetPasswordViewFour");
    }

}
