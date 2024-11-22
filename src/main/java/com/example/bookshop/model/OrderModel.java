package com.example.bookshop.model;

import com.example.bookshop.db.DBConnection;
import com.example.bookshop.dto.OrderDetails;
import com.example.bookshop.dto.OrderDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public class OrderModel {

    public ArrayList<String> getAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT CUST_ID FROM customer";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(resultSet.getString(1));
        }
        return customers;
    }

    public ArrayList<String> getAllDiscounts() throws SQLException, ClassNotFoundException {
        String sql = "SELECT DIS_ID FROM discount";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> discounts = new ArrayList<>();
        while (resultSet.next()) {
            discounts.add(resultSet.getString(1));
        }
        return discounts;
    }

    public ArrayList<String> getAllBooks() throws SQLException, ClassNotFoundException {
        String sql = "SELECT BOOK_ID FROM book";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> books = new ArrayList<>();
        while (resultSet.next()) {
            books.add(resultSet.getString(1));
        }
        return books;
    }

    public ArrayList<String> getAllCourier() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COU_ID FROM courier";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> couriers = new ArrayList<>();
        while (resultSet.next()) {
            couriers.add(resultSet.getString(1));
        }
        return couriers;
    }

//    public boolean addOrder(OrderDto orderDto, OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        try {
//            connection.setAutoCommit(false);
//
//            int orderId = saveOrder(connection, orderDto);
//            if (orderId > 0) {
//                orderDetails.setOrderID(orderId);
//
//                double pricePerUnit = getBookPrice(connection, orderDetails.getBookID());
//                orderDetails.setPrice(pricePerUnit * orderDetails.getQuantity());
//
//                if (!saveOrderDetails(connection, orderDetails) || !updateBookQty(connection, orderDetails.getBookID(), orderDetails.getQuantity())) {
//                    connection.rollback();
//                    return false;
//                }
//                connection.commit();
//                return true;
//            }
//            connection.rollback();
//            return false;
//        } catch (SQLException e) {
//            connection.rollback();
//            throw e;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }

    public boolean addOrder(OrderDto orderDto, OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            int orderId = saveOrder(connection, orderDto);
            if (orderId > 0) {
                orderDetails.setOrderID(orderId);

                // Get the price per unit of the book
                double pricePerUnit = getBookPrice(connection, orderDetails.getBookID());
                orderDetails.setPrice(pricePerUnit * orderDetails.getQuantity());

                // Get the discount amount based on the selected discount ID
                double discountAmount = getDiscountAmount(connection, orderDto.getDIS_ID());
                double finalPrice = orderDetails.getPrice() - discountAmount;

                // Set the final price after applying the discount
                orderDetails.setPrice(finalPrice);

                // Save order details and update book quantity
                if (!saveOrderDetails(connection, orderDetails) || !updateBookQty(connection, orderDetails.getBookID(), orderDetails.getQuantity())) {
                    connection.rollback();
                    return false;
                }
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private int saveOrder(Connection connection, OrderDto orderDto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders (O_Date, custID, B_ID, Cou_ID, Dis_ID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setDate(1, orderDto.getOrderDate());
            statement.setInt(2, orderDto.getCustId());
            statement.setInt(3, orderDto.getB_ID());
            statement.setInt(4, orderDto.getCOU_ID());
            statement.setInt(5, orderDto.getDIS_ID());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        }
       return -1;
    }

    private boolean saveOrderDetails(Connection connection, OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO order_details (book_id, o_id, Price, qty) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderDetails.getBookID());
            statement.setInt(2, orderDetails.getOrderID());
            statement.setDouble(3, orderDetails.getPrice());
            statement.setInt(4, orderDetails.getQuantity());

            return statement.executeUpdate() > 0;
        }

    }

    public double getBookPrice(Connection connection, int bookID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Price FROM book WHERE BOOK_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("Price");
            }
        }
        return 0.0;

    }

    private boolean updateBookQty(Connection connection, int bookID, int quantity) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE book SET qty = qty - ? WHERE BOOK_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, bookID);
            return statement.executeUpdate() > 0;
        }
    }

    private double getDiscountAmount(Connection connection, int discountId) throws SQLException {
        String sql = "SELECT Amount FROM discount WHERE DIS_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, discountId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("Amount");
            }
        }
        return 0.0; // Return 0 if no discount found
    }
}
