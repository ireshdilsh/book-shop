package com.example.bookshop.model;

import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.SQLException;

public class CustomerModel {
    public String addNewCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into customer(Name,Address,Contact) values(?,?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,customerDto.getCustName(),customerDto.getCustAddress(),customerDto.getCustPhone());
        return resp == Boolean.TRUE ? "success" : "error";
    }
}
