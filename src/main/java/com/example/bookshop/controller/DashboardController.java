package com.example.bookshop.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.dto.tm.CustomerTM;
import com.example.bookshop.model.CustomerModel;
import com.example.bookshop.utils.WindowUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    public AnchorPane dashboardPane;
    @FXML
    private ImageView closeButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView closeButtnforModel;
    @FXML
    private ImageView bookImageinBokkView;

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

    // end dashboard model opens section

   // table view
   @FXML
   private TableColumn<CustomerTM, String> addressCol;
    @FXML
    private TableColumn<CustomerTM, Integer> contactCol;
    @FXML
    private TableView<CustomerTM> customerTable;
    @FXML
    private TableColumn<CustomerTM, String> nameCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final CustomerModel customerModel = new CustomerModel();

    public void getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDTOS = customerModel.getAllCustomers();

        ObservableList<CustomerTM> customerTMS = FXCollections.observableArrayList();

        for (CustomerDto customerDTO : customerDTOS) {
            CustomerTM customerTM = new CustomerTM(
                    customerDTO.getCustName(),
                    customerDTO.getCustAddress(),
                    customerDTO.getCustPhone()
            );
            customerTMS.add(customerTM);
        }

        customerTable.setItems(customerTMS);
    }
}
