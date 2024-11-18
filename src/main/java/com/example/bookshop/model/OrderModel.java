package com.example.bookshop.model;

import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public ArrayList<String> getAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "select CUST_ID from customer";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> customer = new ArrayList<>();

        while (resultSet.next()){
            customer.add(resultSet.getString(1));
        }
        return customer;
    }

    public ArrayList<String> getAllDiscounts() throws SQLException, ClassNotFoundException {
        String sql = "select DIS_ID from discount";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> discount = new ArrayList<>();

        while (resultSet.next()){
            discount.add(resultSet.getString(1));
        }
        return discount;
    }

    public ArrayList<String> getAllBook() throws SQLException, ClassNotFoundException {
        String sql = "select BOOK_ID from book";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> book = new ArrayList<>();

        while (resultSet.next()){
            book.add(resultSet.getString(1));
        }
        return book;
    }

    public ArrayList<String> getAllCourier() throws SQLException, ClassNotFoundException {
        String sql = "select COU_ID from courier";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> courier = new ArrayList<>();

        while (resultSet.next()){
            courier.add(resultSet.getString(1));
        }
        return courier;
    }
}
