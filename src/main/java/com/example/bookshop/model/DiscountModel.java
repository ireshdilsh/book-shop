package com.example.bookshop.model;

import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.dto.DiscountDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountModel {
    public DiscountDto findById(String selectedDiscountId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeCrud("select * from discount where DIS_ID=?", selectedDiscountId);

        if (rst.next()) {
            return new DiscountDto(
                  rst.getDouble(2),
                  rst.getString(3)
            );
        }
        return null;
    }
}
