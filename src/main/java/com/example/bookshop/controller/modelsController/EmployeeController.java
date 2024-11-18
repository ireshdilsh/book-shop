package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.EmployeeDto;
import com.example.bookshop.model.EmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private final EmployeeModel employeeModel = new EmployeeModel();

    @FXML
    private TextField ageTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField conactTxt;

    @FXML
    private TextField employeeNameTxt;

    @FXML
    private ComboBox<String> promotionComboTxt;

    @FXML
    private ComboBox<String> salaryComboTxt;

    @FXML
    void addNewEmployee(ActionEvent event) throws SQLException, ClassNotFoundException {
       if (employeeNameTxt.getText().isEmpty() && conactTxt.getText().isEmpty() && ageTxt.getText().isEmpty() && promotionComboTxt.getItems().isEmpty() && salaryComboTxt.getItems().isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Please fill out all fields!").showAndWait();
       } else if (employeeNameTxt.getText().isEmpty() || conactTxt.getText().isEmpty() || ageTxt.getText().isEmpty() || promotionComboTxt.getItems().isEmpty() || salaryComboTxt.getItems().isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Please fill out all fields!").showAndWait();
       }else{
           // getting combo box index
           EmployeeDto employeeDto = new EmployeeDto(employeeNameTxt.getText(), Integer.parseInt(conactTxt.getText()),Integer.parseInt(ageTxt.getText()),promotionComboTxt.getSelectionModel().getSelectedIndex()+1,salaryComboTxt.getSelectionModel().getSelectedIndex()+1);
           String resp = employeeModel.addNewEmployee(employeeDto);

           if (resp.equals("success")) {
               new Alert(Alert.AlertType.INFORMATION,"Employee added successfully!").show();
               clearFields();
           }else{
               new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
           }
       }
    }

    public void clearFields(){
        employeeNameTxt.setText("");
        conactTxt.setText("");
        ageTxt.setText("");
        promotionComboTxt.getItems().clear();
        salaryComboTxt.getItems().clear();
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadSalaryComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            loadPromotionComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSalaryComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> salaries = employeeModel.getAllSalaries();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(salaries);
        salaryComboTxt.setItems(observableList);
    }

    private void loadPromotionComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String>promotions = employeeModel.getAllPromotions();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(promotions);
        promotionComboTxt.setItems(observableList);
    }
}
