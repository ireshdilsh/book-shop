module com.example.bookshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires static lombok;
    requires com.jfoenix;

    opens com.example.bookshop to javafx.fxml;
    opens com.example.bookshop.controller to javafx.fxml;
    opens com.example.bookshop.controller.modelsController to javafx.fxml;

    exports com.example.bookshop;
    exports com.example.bookshop.controller;
}