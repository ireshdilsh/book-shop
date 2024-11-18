package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.SupplierDto;
import com.example.bookshop.model.SupplierModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SupplierController {

    private final SupplierModel supplierModel = new SupplierModel();

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField supplierContactTxt;

    @FXML
    private TextField supplierNameTxt;

    @FXML
    void addNewSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (supplierContactTxt.getText().isEmpty() && supplierNameTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot empty this fields.").show();
        } else if (supplierContactTxt.getText().isEmpty() || supplierNameTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot empty this fields.").show();
        }else{
            SupplierDto supplierDto = new SupplierDto(supplierNameTxt.getText(), Integer.parseInt(supplierContactTxt.getText()));
            String resp = supplierModel.addNewSupplier(supplierDto);

            if (resp.equals("success")) {
                new Alert(Alert.AlertType.INFORMATION,"Supplier added successfully").show();
                clearFields();
            }
        }
    }

    public void clearFields(){
        this.supplierContactTxt.setText("");
        this.supplierNameTxt.setText("");
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }
}
