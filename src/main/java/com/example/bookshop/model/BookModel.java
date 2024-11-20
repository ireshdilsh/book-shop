package com.example.bookshop.model;
import com.example.bookshop.db.DBConnection;
import com.example.bookshop.dto.BookDetailsDto;
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

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean isBookSaved = CrudUtil.executeCrud(
                    "insert into book(Name,CatName,Price,Supplier_Name,qty) values (?,?,?,?,?)",
                  bookDto.getBookName(),bookDto.getCategoryName(),bookDto.getPrice(),bookDto.getSuplierName(),bookDto.getQty()
            );

            if (isBookSaved) {

                boolean bookDesilasSaved = bookDetailsModel.saveBookDetailsList(bookDto.getBookDetailsDtos());
                if (bookDesilasSaved) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
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


        public BookDto findById(String selectedBookId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeCrud("select * from book where BOOK_ID=?", selectedBookId); if (rst.next()) { // Assuming the 5th column contains a serialized ArrayList of BookDetailsDto objects
             Object object = rst.getObject(5);
             ArrayList<BookDetailsDto> suppliers = null;
             if (object instanceof ArrayList<?>) {
                 suppliers = (ArrayList<BookDetailsDto>) object;
             } else {
                 suppliers = new ArrayList<>();
             } return new BookDto(
                     rst.getString(2),
                        rst.getString(3),
                        rst.getDouble(4),
                        suppliers );
        } return null;

        }
}
