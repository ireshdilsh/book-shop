package com.example.bookshop.model;

import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public String addNewCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into customer(Name,Address,Contact) values(?,?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,customerDto.getCustName(),customerDto.getCustAddress(),customerDto.getCustPhone());
        return resp == Boolean.TRUE ? "success" : "error";
    }

    public CustomerDto findById(String selectedCustomerId) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeCrud("select * from customer where CUST_ID=?", selectedCustomerId);

        if (rst.next()) {
            return new CustomerDto(
                //    rst.getInt(1),  // Customer ID
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }
}
