package com.example.bookshop.model;

import com.example.bookshop.dto.SupplierDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.SQLException;

public class SupplierModel {
    public String addNewSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into supplier(Name,Contact) values(?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,supplierDto.getSupplierName(),supplierDto.getContactNo());
        return resp == Boolean.TRUE ? "success" : "fail";
    }
}
