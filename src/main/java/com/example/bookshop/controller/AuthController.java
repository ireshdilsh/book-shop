package com.example.bookshop.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.model.UserModel;
import com.example.bookshop.utils.WindowUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuthController {

    private final UserModel userModel = new UserModel();

    @FXML
    private PasswordField loginPasswordTxt;
    @FXML
    private TextField loginEmailTxt;
    @FXML
    private TextField registerIUsernameTxt;
    @FXML
    private TextField registerEmailTxt;
    @FXML
    private PasswordField registerPasswordTxt;
    @FXML
    private ImageView closeButton;
    @FXML
    private AnchorPane mainPane;

      public void openForgetPasswordView(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButton,"ForgetPasswordViewOne");
    }

     public void closeWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void gotoSignUpWindow(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(mainPane,"SignupView");
    }

    public void gotoSignInWindow(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(mainPane,"SigninView");
    }

    // user account create method
    public void creaateAccount(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (registerEmailTxt.getText().isEmpty() && registerIUsernameTxt.getText().isEmpty()
                && registerPasswordTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot be empty this fields").show();
        } else if (registerEmailTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot be empty Email field").show();
        } else if (registerIUsernameTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot be empty Username field").show();
        } else if (registerPasswordTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot be empty Password field").show();
        } else {
            UserDto userDto = new UserDto(registerEmailTxt.getText(), registerIUsernameTxt.getText(), registerPasswordTxt.getText());
            String resp = userModel.createAccount(userDto);
            if (resp.equals("success")) {
                new Alert(javafx.scene.control.Alert.AlertType.INFORMATION,"Account Created Successfully !").show();
                clearTextFields();
                new WindowUtil().navigateTo(mainPane,"SigninView");
            }else{
                new Alert(javafx.scene.control.Alert.AlertType.ERROR,"Account Create Failed !").show();
            }
        }
    }

    // user login validation method
    public void gotoDashboard(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (loginEmailTxt.getText().isEmpty() && loginPasswordTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "This fields cannot Empty.").show();
        } else if (loginEmailTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot be empty Email field.").show();
        } else if (loginPasswordTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot be empty Password field.").show();
        } else {
            UserDto userDto = new UserDto(loginEmailTxt.getText(), loginPasswordTxt.getText());
            String resp = userModel.loginAuthentication(userDto);
            System.out.println(resp);

            if (resp.equals("Login Successfully.")) {
                this.loginEmailTxt.setText("");
                this.loginPasswordTxt.setText("");
                this.mainPane.getScene().getWindow().hide();
                new WindowUtil().setWindow("DashBoardView");
            } else {
                new Alert(javafx.scene.control.Alert.AlertType.ERROR, resp).show();
            }
        }
    }

    public void clearTextFields() {
        this.registerEmailTxt.setText("");
        this.registerIUsernameTxt.setText("");
        this.registerPasswordTxt.setText("");
    }
}
