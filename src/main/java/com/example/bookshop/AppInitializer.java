package com.example.bookshop;

import com.example.bookshop.utils.WindowUtil;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       new WindowUtil().setWindow("MainViewTwo");
    }

    public static void main(String[] args) {launch();}
}