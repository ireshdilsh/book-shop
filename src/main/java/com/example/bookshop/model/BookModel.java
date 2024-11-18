package com.example.bookshop.model;

import com.example.bookshop.db.DBConnection;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookModel {

    private final BookDetailsModel bookDetailsModel = new BookDetailsModel();

    public ArrayList<String> getAllCategories() throws SQLException, ClassNotFoundException {
        String sql = "select Name from category";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> category = new ArrayList<>();

        while (resultSet.next()){
            category.add(resultSet.getString(1));
        }
        return category;
    }

    public boolean addNewBook(BookDto bookDto) throws SQLException, ClassNotFoundException {
//        String sql = "insert into book (Name,CatName,Price) values (?,?,?)";
//        Boolean resp = CrudUtil.executeCrud(sql,bookDto.getBookName(),bookDto.getCategoryName(),bookDto.getPrice());
//        return resp == Boolean.TRUE ? "success" : "error";


        Connection connection = DBConnection.getInstance().getConnection();
        try {
            // @autoCommit: Disables auto-commit to manually control the transaction
            connection.setAutoCommit(false); // 1

            // @isOrderSaved: Saves the order details into the orders table
            boolean isBookSaved = CrudUtil.executeCrud(
                    "insert into book(Name,CatName,Price,Supplier_Name) values (?,?,?,?)",
                  bookDto.getBookName(),bookDto.getCategoryName(),bookDto.getPrice(),bookDto.getSuplierName()
            );
            // If the order is saved successfully
            if (isBookSaved) {
                // @isOrderDetailListSaved: Saves the list of order details
                boolean bookDesilasSaved = bookDetailsModel.saveBookDetailsList(bookDto.getBookDetailsDtos());
                if (bookDesilasSaved) {
                    // @commit: Commits the transaction if both order and details are saved successfully
                    connection.commit(); // 2
                    return true;
                }
            }
            // @rollback: Rolls back the transaction if order details saving fails
            connection.rollback(); // 3
            return false;
        } catch (Exception e) {
            // @catch: Rolls back the transaction in case of any exception
            connection.rollback();
            return false;
        } finally {
            // @finally: Resets auto-commit to true after the operation
            connection.setAutoCommit(true); // 4
        }

    }

    public ArrayList<String> getAllSuppliers() throws SQLException, ClassNotFoundException {
        String sql = "select Name from supplier";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> supplier = new ArrayList<>();

        while (resultSet.next()){
            supplier.add(resultSet.getString(1));
        }
        return supplier;
    }
}
