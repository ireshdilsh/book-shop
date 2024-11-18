package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.RiviewDto;
import com.example.bookshop.model.RiviewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RiviewController {

    private final RiviewModel riviewModel = new RiviewModel();

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField riviewCustNameTxt;

    @FXML
    private TextArea riviewDiscriptionTxt;

    @FXML
    void addNewRiview(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (riviewCustNameTxt.getText().isEmpty() && riviewDiscriptionTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot empty this fields.").show();
        } else if (riviewCustNameTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot empty Customer Name field.").show();
        }else if (riviewDiscriptionTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Cannot empty Discription field.").show();
        }else{
            RiviewDto riviewDto = new RiviewDto(riviewCustNameTxt.getText(), riviewDiscriptionTxt.getText());
            String resp = riviewModel.addNewRiview(riviewDto);

            if (resp.equals("success")) {
                new Alert(Alert.AlertType.INFORMATION,"Riview added successfully").show();
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,"Cannot add Riview. Try again !").show();
            }
        }
    }

    public void clearFields(){
        riviewCustNameTxt.setText("");
        riviewDiscriptionTxt.setText("");
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

}
