package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.BookDetailsDto;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.model.BookModel;
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

public class BookController implements Initializable {

    private final BookModel bookModel = new BookModel();

    @FXML
    private ComboBox<String> supplierDropTxt;

    @FXML
    private TextField bookNameTxt;

    @FXML
    private ComboBox<String> categoryDropdownTxt;

    @FXML
    private ImageView closeModelButton;

    @FXML
    private TextField qtxText;

    @FXML
    private TextField priceTxt;

    @FXML
    void addNewBook(ActionEvent event) throws SQLException, ClassNotFoundException {
       if (bookNameTxt.getText().isEmpty() && categoryDropdownTxt.getItems().isEmpty() && priceTxt.getText().isEmpty()) {
           new Alert(Alert.AlertType.ERROR,"Cannot empty this fields.").show();
       } else if (bookNameTxt.getText().isEmpty() || categoryDropdownTxt.getItems().isEmpty() || priceTxt.getText().isEmpty()) {
           new Alert(Alert.AlertType.ERROR,"Cannot empty this fields.").show();
       }else{

           ArrayList<BookDetailsDto> bookDetailsDtos = new ArrayList<>();

           // Collect data for each item in the cart and add to order details array
           for (BookDetailsDto book : bookDetailsDtos) {

               // Create order details for each cart item
               BookDetailsDto bookDetailsDto = new BookDetailsDto(
                    book.getBookID(),book.getSupplierID()
               );

               // Add to order details array
               bookDetailsDtos.add(bookDetailsDto);
           }

           BookDto bookDto = new BookDto(bookNameTxt.getText(), categoryDropdownTxt.getValue(),Double.parseDouble(priceTxt.getText()),supplierDropTxt.getValue(),Integer.parseInt(qtxText.getText()),bookDetailsDtos);
           boolean resp = Boolean.parseBoolean(String.valueOf(bookModel.addNewBook(bookDto)));

           if (true) {
               new Alert(Alert.AlertType.INFORMATION,"Book has been Successfully Saved !").show();
               clearFields();
           }else{
               new Alert(Alert.AlertType.ERROR,"Something went Wrong!").show();
           }
       }
    }

    public void clearFields() {
        bookNameTxt.setText("");
        categoryDropdownTxt.getItems().clear();
        priceTxt.setText("");
        supplierDropTxt.getItems().clear();
        qtxText.setText("");
    }

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.closeModelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      try {
          getAllCategories();
          getAllSupplier();
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    private void getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<String> suppliers = bookModel.getAllSuppliers();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(suppliers);
        supplierDropTxt.setItems(observableList);
    }

    private void getAllCategories() throws SQLException, ClassNotFoundException {
        ArrayList<String> categories = bookModel.getAllCategories();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(categories);
        categoryDropdownTxt.setItems(observableList);
    }
}
