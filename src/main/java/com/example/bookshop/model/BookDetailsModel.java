package com.example.bookshop.model;

import com.example.bookshop.dto.BookDetailsDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookDetailsModel {
    public boolean saveBookDetailsList(ArrayList<BookDetailsDto> bookDetailsDtos) throws SQLException, ClassNotFoundException {
        for (BookDetailsDto dto : bookDetailsDtos) {
            // @isOrderDetailsSaved: Saves the individual order detail
            boolean isBookdetailsSaved = saveBookDetail(dto);
            if (!isBookdetailsSaved) {
                // Return false if saving any order detail fails
                return false;
            }
        }
        // Return true if all order details are saved and item quantities updated successfully
        return true;
    }

    private boolean saveBookDetail(BookDetailsDto dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into book_supplier values(?,?)";
        return CrudUtil.executeCrud(sql,dto.getBookID(),dto.getSupplierID());
    }
}
