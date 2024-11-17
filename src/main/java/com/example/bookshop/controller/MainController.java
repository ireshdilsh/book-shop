package com.example.bookshop.controller;

import com.example.bookshop.model.UserModel;
import com.example.bookshop.utils.WindowUtil;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // imageViews for add mouse events(like carouels)
        this.imageView1.setOnMouseClicked(mouseEvent -> {
           new WindowUtil().navigateTo(mainPane,"MainViewOne");
        });

        this.imageView2.setOnMouseClicked(mouseEvent -> {
            new WindowUtil().navigateTo(mainPane,"MainViewTwo");
        });

        this.imageView3.setOnMouseClicked(mouseEvent -> {
            new WindowUtil().navigateTo(mainPane,"MainViewThree");
        });

        this.imageView4.setOnMouseClicked(mouseEvent -> {
            new WindowUtil().navigateTo(mainPane,"MainViewFour");
        });

        // signin and signup buttons action events
        this.signinButton.setOnAction(actionEvent -> {
            try {
                new WindowUtil().getLikeModel(closeButton,"SigninView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.signupButton.setOnAction(actionEvent -> {
            try {
                new WindowUtil().getLikeModel(closeButton,"SignupView");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private final UserModel userModel = new UserModel();

    // fx encapsulate fields
    @FXML
    private AnchorPane mainPane;

    @FXML
    private ImageView closeButton;

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private ImageView imageView4;

    @FXML
    private JFXButton signinButton;

    @FXML
    private JFXButton signupButton;

    public void closeWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }
}