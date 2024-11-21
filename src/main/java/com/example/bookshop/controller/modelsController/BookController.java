package com.example.bookshop.controller.modelsController;

import com.example.bookshop.dto.BookDetailsDto;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CategoryDto;
import com.example.bookshop.dto.SupplierDto;
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
import java.util.HashMap;
import java.util.List;
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

    private ObservableList<String> cateName;
    private HashMap<String, Integer> cateMap;
    private HashMap<String, Integer> supplierMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cateName = FXCollections.observableArrayList();
        cateMap = new HashMap<>();
        supplierMap = new HashMap<>();

        try {
            getAllCategories();
            getAllSupplier();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addNewBook(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (bookNameTxt.getText().isEmpty() || categoryDropdownTxt.getValue() == null || priceTxt.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Cannot leave any fields empty.").show();
        } else {
            ArrayList<BookDetailsDto> bookDetailsDtos = new ArrayList<>();
            String selectedSupplier = supplierDropTxt.getValue();
            Integer supplierId = supplierMap.get(selectedSupplier);

            if (supplierId == null) {
                new Alert(Alert.AlertType.ERROR, "Invalid supplier selected.").show();
                return;
            }

            bookDetailsDtos.add(new BookDetailsDto(0, supplierId));
            BookDto bookDto = new BookDto(
                    bookNameTxt.getText(),
                    categoryDropdownTxt.getValue(),
                    Double.parseDouble(priceTxt.getText()),
                    supplierDropTxt.getValue(),
                    Integer.parseInt(qtxText.getText()),
                    bookDetailsDtos
            );

            boolean resp = bookModel.addNewBook(bookDto);
            if (resp) {
                new Alert(Alert.AlertType.INFORMATION, "Book has been Successfully Saved!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
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

    private void getAllSupplier() throws SQLException, ClassNotFoundException {
        List<SupplierDto> suppliers = bookModel.getAllSuppliers();
        ObservableList<String> supplierNames = FXCollections.observableArrayList();
        for (SupplierDto supplier : suppliers) {
            supplierNames.add(supplier.getSupplierName());
            supplierMap.put(supplier.getSupplierName(), bookModel.getSupplierId());
        }
        supplierDropTxt.setItems(supplierNames);
    }

    private void getAllCategories() throws SQLException, ClassNotFoundException {
        List<CategoryDto> categories = bookModel.getAllCategories();
        for (CategoryDto category : categories) {
            cateName.add(category.getCategoryName());
            cateMap.put(category.getCategoryName(), category.getCategoryId());
        }
        categoryDropdownTxt.setItems(cateName);
    }
}
