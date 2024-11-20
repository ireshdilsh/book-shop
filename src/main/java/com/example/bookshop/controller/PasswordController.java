package com.example.bookshop.controller;

import com.example.bookshop.dto.PasswordDto;
import com.example.bookshop.model.UserModel;
import com.example.bookshop.utils.MailUtil;
import com.example.bookshop.utils.WindowUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PasswordController implements Initializable {

    private final UserModel userModel = new UserModel();

    @FXML
    private AnchorPane passwordMainPane;

    @FXML
    private ImageView closeButton;

    @FXML
    private TextField emailText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private TextField confirmationCodeText;

    @FXML
    private TextField emailText1;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // close password change windows.
        this.closeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) this.closeButton.getScene().getWindow();
            stage.close();
        });
    }

    public void gotoPasswordChangeViewTwo(ActionEvent actionEvent) throws Exception {
        String email = emailText1.getText();
        boolean resp = userModel.getEmailByUser(email);

        if (resp == Boolean.TRUE) {
            MailUtil.sendMail(email);
            new Alert(Alert.AlertType.INFORMATION,"Check your email for get a validation code").show();
            new WindowUtil().navigateTo(passwordMainPane,"ForgetPasswordViewTwo");
        }else{
            new Alert(Alert.AlertType.ERROR,"Email Address is Invalid.Please check your Email.").show();
        }
    }

    public void gotoForgetPasswordViewThree(ActionEvent actionEvent) {
        int confirmationCode = new MailUtil().generateNumber();
        int code = Integer.parseInt(emailText1.getText());

        if (code == confirmationCode) {
            new WindowUtil().navigateTo(passwordMainPane, "ForgetPasswordViewThree");
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Verificvation Code.Please check your Code.").show();
        }
    }

    public void gotoForgetPasswordViewFour(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (emailText.getText().isEmpty() && (passwordText.getText().isEmpty())) {
            new Alert(Alert.AlertType.ERROR,"Cannot empty this fields.").show();
        }else if(emailText.getText().isEmpty() || passwordText.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Cannot empty this field.").show();
        }else{
            PasswordDto passwordDto = new PasswordDto(emailText.getText(), passwordText.getText());
            String resp = userModel.changePassword(passwordDto);

            if (resp.equals("success")) {
                new WindowUtil().navigateTo(passwordMainPane,"ForgetPasswordViewFour");
            }else{
                new Alert(Alert.AlertType.ERROR,"Cannot change password.Something went wrong.").show();
            }
        }
    }
}
