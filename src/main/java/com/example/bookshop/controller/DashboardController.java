package com.example.bookshop.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.dto.EmployeeDto;
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
            getAllCustomerName();
            getAllBookName();
            getEmployeeNames();
            getSalaryAmount();
            getPromotionType();
           // getAllRiviewsTable();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //---------------------------------//
        customerNameComboBox.setOnAction(event -> {
            String name = customerNameComboBox.getSelectionModel().getSelectedItem();
            CustomerDto customerDto = null;
            try {
                getAllCustomerName();
                customerDto = customerModel.findByName(name);
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (customerDto != null){
                addressLbl.setText(customerDto.getCustAddress());
                contactLbl.setText(String.valueOf(customerDto.getCustPhone()));
            }
        });
        //---------------------------------//
        bookComboBox.setOnAction(event -> {
            String name = bookComboBox.getSelectionModel().getSelectedItem();
            BookDto bookDto = null;
            try {
                getAllBookName();
                bookDto = bookModel.findByName(name);
            } catch (Exception e) {
              e.printStackTrace();
            }
            if (bookDto != null){
                categoryNameLbl.setText(bookDto.getCategoryName());
                bookPriceLbl.setText(String.valueOf(bookDto.getPrice()));
                supplierNameLbl.setText(bookDto.getSuplierName());
                qtyText1.setText(String.valueOf(bookDto.getQty()));
            }
        });
        //---------------------------------//
        employeeComboBox.setOnAction(event -> {
            String name = employeeComboBox.getSelectionModel().getSelectedItem();
            EmployeeDto employeeDto = null;
            try {
                getEmployeeNames();
                employeeDto = employeeModel.findByName(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (employeeDto != null){
                ageLbl.setText(String.valueOf(employeeDto.getAge()));
                empContactLbl.setText(String.valueOf(employeeDto.getContact()));
            }
        });
    }

    public AnchorPane dashboardPane;
    @FXML
    private ImageView closeButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView closeButtnforModel;


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

    @FXML
    private Label addressLbl;

    @FXML
    private TextField addressTxt;
    @FXML
    private Label contactLbl;

    @FXML
    private TextField contactTxt;

    @FXML
    private ComboBox<String> customerNameComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> customerNameComboBox1 = new ComboBox<>();
    @FXML
    private ComboBox<String> customerNameComboBox2 = new ComboBox<>();

    public void getAllCustomerName() throws SQLException, ClassNotFoundException {
        ArrayList<String> customers = customerModel.getAllCustomerName();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customers);
        customerNameComboBox.setItems(observableList);
        customerNameComboBox1.setItems(observableList);
        customerNameComboBox2.setItems(observableList);
    }

    public void updateCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDto customerDto = new CustomerDto(customerNameComboBox1.getSelectionModel().getSelectedItem(),
                addressTxt.getText(),Integer.parseInt(contactTxt.getText()));
        String resp = customerModel.updateCustomer(customerDto);

        if (resp.equals("success")) {
            new Alert(Alert.AlertType.INFORMATION,"Customer updated successfully.").show();
            customerNameComboBox1.getItems().clear();
            addressTxt.setText("");
            contactTxt.setText("");
        }else{
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong !").show();
        }
    }

    public void deleteCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = customerNameComboBox2.getSelectionModel().getSelectedItem();
        String resp = customerModel.deleteCustomer(name);
        if (resp.equals("success")) {
            new Alert(Alert.AlertType.INFORMATION,"Customer deleted successfully.").show();
            customerNameComboBox2.getItems().clear();
            addressTxt.setText("");
            contactTxt.setText("");
            getAllCustomerName();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong !").show();
        }
    }

    //-------------------------------------------------//
    @FXML
    private ComboBox<String> bookComboBox = new ComboBox<>();
    @FXML
    private Label bookPriceLbl;
    @FXML
    private Label categoryNameLbl;
    @FXML
    private Label supplierNameLbl;
    @FXML
    private Label qtyText1;
    @FXML
    private ComboBox<String> bookComboBox2 = new ComboBox<>();
    public void getAllBookName() throws SQLException, ClassNotFoundException {
        ArrayList<String> books = customerModel.getAllBookName();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(books);
        bookComboBox.setItems(observableList);
        bookComboBox2.setItems(observableList);
    }

    public void deleteBook(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = bookComboBox2.getSelectionModel().getSelectedItem();
        String resp = bookModel.deleteBook(name);

        if (resp.equals("success")) {
            new Alert(Alert.AlertType.INFORMATION,"Book deleted successfully.").show();
            bookComboBox2.getItems().clear();
            getAllBookName();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong !").show();
        }
    }

    //-----------------------------------------------------//

    @FXML
    private Label ageLbl;
    @FXML
    private TextField empAgeTxt;
    @FXML
    private Label empContactLbl;
    @FXML
    private TextField empContactTxt;
    @FXML
    private ComboBox<String> empPromotionComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> empSalaryComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> employeeComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> employeeComboBox1 = new ComboBox<>();

    public void getEmployeeNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> names = employeeModel.getAllEmployeeName();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(names);
        employeeComboBox.setItems(observableList);
        employeeComboBox1.setItems(observableList);
    }

    public void getSalaryAmount() throws SQLException, ClassNotFoundException {
        ArrayList<String> salaries = employeeModel.getAllSalaries();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(salaries);
        empSalaryComboBox.setItems(observableList);
    }

    public void getPromotionType() throws SQLException, ClassNotFoundException {
        ArrayList<String> promos = employeeModel.getAllPromotions();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(promos);
        empPromotionComboBox.setItems(observableList);
    }

    public void updateEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeeDto employeeDto = new EmployeeDto(
                employeeComboBox1.getSelectionModel().getSelectedItem(),
                Integer.parseInt( empContactTxt.getText()),
                Integer.parseInt(empAgeTxt.getText()),
                empPromotionComboBox.getSelectionModel().getSelectedIndex()+1,
                empSalaryComboBox.getSelectionModel().getSelectedIndex()+1
        );

        String resp = employeeModel.updateEmployee(employeeDto);
        if (resp.equals("success")){
            new Alert(Alert.AlertType.INFORMATION,"Employee Update Sucessful.").show();
            empPromotionComboBox.getItems().clear();
            empAgeTxt.setText("");
            empContactTxt.setText("");
            employeeComboBox1.getItems().clear();
            empSalaryComboBox.getItems().clear();
            getEmployeeNames();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went Wrong.").show();
        }
    }

    public void deleteEmployee(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = employeeComboBox1.getSelectionModel().getSelectedItem();
        String resp = employeeModel.deleteEmployee(name);

        if (resp.equals("success")) {
            new Alert(Alert.AlertType.INFORMATION,"Employee deleted successfully.").show();
            employeeComboBox1.getItems().clear();
            getEmployeeNames();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something Went Wrong !").show();
        }
    }

    //-----------------------------------------------------//

}