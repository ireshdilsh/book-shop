package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.model.CustomerModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CustomerController {

    private final CustomerModel customerModel = new CustomerModel();

    @FXML
    private TextField addressTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField conactTxt;

    @FXML
    private TextField custNameTxt;

    public void addNewCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (custNameTxt.getText().isEmpty() && addressTxt.getText().isEmpty() && conactTxt.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
        } else if (custNameTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please fill customer name field.").show();
        } else if (addressTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please fill address field.").show();
        }else if (conactTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Please fill conact field.").show();
        }else{
            CustomerDto customerDto = new CustomerDto(custNameTxt.getText(), addressTxt.getText(), Integer.parseInt(conactTxt.getText()));
            String resp = customerModel.addNewCustomer(customerDto);
            if (resp.equals("success")){
                new Alert(Alert.AlertType.INFORMATION, "Customer added successfully").show();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong.").show();
            }
        }
    }

    public void clearFields() {
        custNameTxt.setText("");
        addressTxt.setText("");
        conactTxt.setText("");
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

}
