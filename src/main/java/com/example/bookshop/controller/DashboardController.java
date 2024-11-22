package com.example.bookshop.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.bookshop.dto.RiviewDto;
import com.example.bookshop.dto.tm.CustomerTM;
import com.example.bookshop.dto.tm.RiviewTM;
import com.example.bookshop.model.*;
import com.example.bookshop.utils.WindowUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getAllBooks();
            getAllRiviewsTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnchorPane dashboardPane;
    @FXML
    private ImageView closeButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView closeButtnforModel;
    @FXML
    private ImageView bookImageinBokkView;

    // -------------------------------------------------- //
    @FXML
    private TableColumn<RiviewTM, String> discription ;
    @FXML
    private TableView<RiviewTM> riviewTable ;
    @FXML
    private TableColumn<RiviewTM, String> custName;

    public void getAllRiviewsTable() throws SQLException, ClassNotFoundException {

        custName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        discription.setCellValueFactory(new PropertyValueFactory<>("discription"));

        ArrayList<RiviewDto> riviewDtos = riviewModel.getAllRiviewsTable();

        ObservableList<RiviewTM> riviewTMS = FXCollections.observableArrayList();

        for (RiviewDto riviewDto : riviewDtos) {
            RiviewTM riviewTM = new RiviewTM(
                riviewDto.getCustName(),riviewDto.getDiscription()
            );
            riviewTMS.add(riviewTM);
        }

        riviewTable.setItems(riviewTMS);
    }

    // -------------------------------------------------- //


    private final BookModel bookModel = new BookModel();
    private final OrderModel orderModel= new OrderModel();
    private final CustomerModel customerModel = new CustomerModel();
    private final EmployeeModel employeeModel = new EmployeeModel();
    private final RiviewModel riviewModel = new RiviewModel();
    private final SupplierModel supplierModel = new SupplierModel();

    public void closeWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
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
    public void openAddNewBookModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel, "model_views/BookModelView");
    }

    public void openAddNewCustomerModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel, "model_views/CustomerModelView");
    }

    public void openAddNewEmployeeModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel, "model_views/EmployeeModelView");
    }

    public void openAddNewOrderModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel, "model_views/OrdersModelView");
    }

    public void openAddNewRiviewModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel, "model_views/RiviewModelView");
    }

    public void openAddNewSupplierModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel, "model_views/SupplierModelView");
    }

    public void openBookUpdateModel(ActionEvent actionEvent) throws IOException {
        new WindowUtil().getLikeModel(closeButtnforModel,"model_views/UpdateBookQTYView");
    }
    // end dashboard model opens section

    @FXML
    private ComboBox<String> updateComboBookName = new ComboBox<>();

    @FXML
    private TextField updateQTYText;

    @FXML
    private ImageView updateViewCloseButton;

    @FXML
    void closeModel(MouseEvent event) {
        Stage stage = (Stage) this.updateViewCloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateQTY(ActionEvent event) throws SQLException, ClassNotFoundException {
        int qty = Integer.parseInt(updateQTYText.getText());
        String bookName = updateComboBookName.getValue();

        String resp = bookModel.updateBookQty(qty,bookName);
        if (resp.equals("success")) {
            new Alert(Alert.AlertType.INFORMATION,"Book QTY updated successfully.").show();
            updateQTYText.setText("");
            updateComboBookName.getItems().clear();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong !").show();
        }
    }


    public void getAllBooks() throws SQLException, ClassNotFoundException {
        ArrayList<String> books = bookModel.getAllBooks();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(books);
        updateComboBookName.setItems(observableList);
    }
}