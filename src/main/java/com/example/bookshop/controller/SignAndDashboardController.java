package com.example.bookshop.controller;

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

import java.io.IOException;
import java.sql.SQLException;


public class SignAndDashboardController {

    private final UserModel userModel = new UserModel();

    @FXML
    private PasswordField loginPasswordTxt;
    @FXML
    private TextField loginEmailTxt;
    @FXML
    private PasswordField registerIUsernameTxt;
    @FXML
    private TextField registerEmailTxt;
    @FXML
    private PasswordField registerPasswordTxt;
    @FXML
    private AnchorPane dashboardPane;
    @FXML
    private ImageView closeButton;
    @FXML
    private AnchorPane mainPane;

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

    public void openForgetPasswordView(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButton,"ForgetPasswordViewOne");
    }

    // dashboard naviagte section start
    public void BookDashboard(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(dashboardPane,"DashboardView");
    }

    public void riviewDashboard(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(dashboardPane,"RiviweDashboard");
    }

    public void orderDashboard(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(dashboardPane,"OrderDashboard");
    }

    public void employeeDashboard(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(dashboardPane,"EmployeeDashboard");
    }

    public void supplierDashboard(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(dashboardPane,"SupplierDashboard");
    }

    public void customerDashboard(ActionEvent actionEvent) {
        new WindowUtil().navigateTo(dashboardPane,"CustomerDashboard");
    }

    // logout method
    public void gotoLogin(ActionEvent actionEvent) {
        this.dashboardPane.getScene().getWindow().hide();
        new WindowUtil().setWindow("MainViewTwo");
    }

    public void closeDashboard(MouseEvent mouseEvent) {
        Stage stage = (Stage) dashboardPane.getScene().getWindow();
        stage.close();
    }
    // dashboard navigate section end

    // start dashboard model opens section
    public void openAddNewBookModel(ActionEvent actionEvent) {
    }

    public void openAddNewCustomerModel(ActionEvent actionEvent) {
    }

    public void openAddNewEmployeeModel(ActionEvent actionEvent) {
    }

    public void openAddNewOrderModel(ActionEvent actionEvent) {
    }

    public void openAddNewRiviewModel(ActionEvent actionEvent) {
    }

    public void openAddNewSupplierModel(ActionEvent actionEvent) {
    }
    // end dashboard model opens section

    // user account create method
    public void creaateAccount(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (registerEmailTxt.getText().isEmpty() && registerIUsernameTxt.getText().isEmpty() && registerPasswordTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot be empty this fields").show();
        } else if (registerEmailTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot be empty Email field").show();
        } else if (registerIUsernameTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot be empty Username field").show();
        } else if (registerPasswordTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot be empty Password field").show();
        }else{
            UserDto userDto = new UserDto(registerEmailTxt.getText(), registerIUsernameTxt.getText(), registerPasswordTxt.getText());
            String resp = userModel.createAccount(userDto);
            new Alert(Alert.AlertType.INFORMATION,resp).show();
            clearTextFields();
            new WindowUtil().navigateTo(mainPane, "SigninView");
        }
    }

    // user login validation method
    public void gotoDashboard(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
      
        if (loginEmailTxt.getText().isEmpty() && loginPasswordTxt.getText().isEmpty()){
           new Alert(Alert.AlertType.ERROR,"This fields cannot Empty.").show();
       } else if (loginEmailTxt.getText().isEmpty()) {
           new Alert(Alert.AlertType.ERROR,"Cannot be empty Email field.").show();
       } else if (loginPasswordTxt.getText().isEmpty()) {
           new Alert(Alert.AlertType.ERROR,"Cannot be empty Password field.").show();
       }else{
           UserDto userDto = new UserDto(loginEmailTxt.getText(), loginPasswordTxt.getText());
           String resp = userModel.loginAuthentication(userDto);
           System.out.println(resp);

           if (resp.equals("Login Successfully.")) {
               this.loginEmailTxt.setText("");
               this.loginPasswordTxt.setText("");
               this.mainPane.getScene().getWindow().hide();
               new WindowUtil().setWindow("DashboardView");
           }else{
            new Alert(javafx.scene.control.Alert.AlertType.ERROR,resp).show();
           }
       }
    }

    public void clearTextFields(){
        this.registerEmailTxt.setText("");
        this.registerIUsernameTxt.setText("");
        this.registerPasswordTxt.setText("");
    }
}
