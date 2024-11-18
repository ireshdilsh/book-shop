package com.example.bookshop.model;

import com.example.bookshop.dto.CourierDto;
import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierModel {
    public CourierDto findById(String selectedCourierId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeCrud("select * from courier where COU_ID=?", selectedCourierId);

        if (rst.next()) {
            return new CourierDto(
                    //    rst.getInt(1),  // Customer ID
                   rst.getString(2)
            );
        }
        return null;
    }
}
