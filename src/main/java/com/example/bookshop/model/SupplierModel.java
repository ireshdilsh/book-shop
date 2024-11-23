package com.example.bookshop.model;

import com.example.bookshop.dto.SupplierDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public String addNewSupplier(SupplierDto supplierDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into supplier(Name,Contact) values(?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,supplierDto.getSupplierName(),supplierDto.getContactNo());
        return resp == Boolean.TRUE ? "success" : "fail";
    }

    public ArrayList<String> getAllSupplierNames() throws SQLException, ClassNotFoundException {
        String sql = "select Name from supplier";
        ResultSet resultSet = CrudUtil.executeCrud(sql);
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getString("Name"));
        }
        return list;
    }

    public SupplierDto findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "select Contact from supplier where Name = ?";
        ResultSet resultSet = CrudUtil.executeCrud(sql,name);

        if (resultSet.next()) {
            return new SupplierDto(
                    resultSet.getInt(1)
            );
        }
        return null;
    }

    public String deleteSupplier(String name) throws SQLException, ClassNotFoundException {
        String sql = "delete from supplier where Name = ?";
        Boolean resp = CrudUtil.executeCrud(sql,name);
        return resp == Boolean.TRUE ? "success" : "fail";
    }

    public String updateSupplier(SupplierDto supplierDto, String name) throws SQLException, ClassNotFoundException {
        String sql = "update supplier set Name = ?,Contact = ? where Name = ?";
        Boolean resp = CrudUtil.executeCrud(sql,supplierDto.getSupplierName(),supplierDto.getContactNo(),name);
        return resp == Boolean.TRUE ? "success" : "fail";
    }
}
