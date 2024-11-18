package com.example.bookshop.model;

import com.example.bookshop.dto.BookDetailsDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookDetailsModel {
    public boolean saveBookDetailsList(ArrayList<BookDetailsDto> bookDetailsDtos) throws SQLException, ClassNotFoundException {

        for (BookDetailsDto dto : bookDetailsDtos) {

            boolean isBookdetailsSaved = saveBookDetail(dto);
            if (!isBookdetailsSaved) {
                return false;
            }
        }

        return true;
    }

    private boolean saveBookDetail(BookDetailsDto dto) throws SQLException, ClassNotFoundException {
        String sql = "insert into book_supplier values(?,?)";
        return CrudUtil.executeCrud(sql,dto.getBookID(),dto.getSupplierID());
    }
}
