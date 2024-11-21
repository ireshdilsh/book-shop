package com.example.bookshop.model;

import com.example.bookshop.db.DBConnection;
import com.example.bookshop.dto.BookDetailsDto;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.CategoryDto;
import com.example.bookshop.dto.SupplierDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel {

    public List<CategoryDto> getAllCategories() throws SQLException, ClassNotFoundException {
        String sql = "SELECT CAT_ID, Name FROM category";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        List<CategoryDto> categories = new ArrayList<>();
        while (resultSet.next()) {
            categories.add(new CategoryDto(resultSet.getInt(1), resultSet.getString(2)));
        }
        return categories;
    }

    public List<SupplierDto> getAllSuppliers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUP_ID, Name FROM supplier";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        List<SupplierDto> suppliers = new ArrayList<>();
        while (resultSet.next()) {
            suppliers.add(new SupplierDto(resultSet.getString(2), resultSet.getInt(1)));
        }
        return suppliers;
    }

    public boolean addNewBook(BookDto bookDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            int bookId = saveBook(connection, bookDto);
            if (bookId > 0) {
                for (BookDetailsDto bookDetails : bookDto.getBookDetailsDtos()) {
                    if (!saveBookDetails(connection, bookId, bookDetails.getSupplierID())) {
                        connection.rollback();
                        return false;
                    }
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

    private int saveBook(Connection connection, BookDto bookDto) throws SQLException {
        String sql = "INSERT INTO book (Name, CatName, Price, Supplier_Name,qty) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, bookDto.getBookName());
            statement.setString(2, bookDto.getCategoryName());
            statement.setDouble(3, bookDto.getPrice());
            statement.setString(4, bookDto.getSuplierName());
            statement.setInt(5, bookDto.getQty());

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

    private boolean saveBookDetails(Connection connection, int bookId, int supplierId) throws SQLException {
        String sql = "INSERT INTO book_supplier (BOOK_ID, SUP_ID) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            statement.setInt(2, supplierId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }
    }

    public Integer getSupplierId() throws SQLException, ClassNotFoundException {
        String sql = "select SUP_ID from supplier";
        ResultSet resultSet = CrudUtil.executeCrud(sql);
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return -1;
    }

    public ArrayList<String> findById(String selectedBookId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Name FROM book WHERE BOOK_ID = ?";
        ResultSet resultSet = CrudUtil.executeCrud(sql,selectedBookId);

        ArrayList<String> books = new ArrayList<>();

        while (resultSet.next()) {
            books.add(resultSet.getString(1));
        }
        return books;
    }
}
