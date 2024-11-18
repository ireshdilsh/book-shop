package com.example.bookshop.model;

import com.example.bookshop.dto.EmployeeDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {
    public ArrayList<String> getAllSalaries() throws SQLException, ClassNotFoundException {
        String sql = "select Amount from salary";
        ResultSet rst = CrudUtil.executeCrud(sql);

        ArrayList<String> salary = new ArrayList<>();

        while (rst.next()) {
            salary.add(rst.getString(1));
        }

        return salary;
    }

    public ArrayList<String> getAllPromotions() throws SQLException, ClassNotFoundException {
        String sql = "select New_Pos from prmotion";
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        ArrayList<String> promotion = new ArrayList<>();

        while (resultSet.next()){
            promotion.add(resultSet.getString(1));
        }
        return promotion;
    }

    public String addNewEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into employee(Name,Contact,Age,PRO_ID,SAL_ID) values(?,?,?,?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,employeeDto.getEmpName(),employeeDto.getContact(),employeeDto.getAge(),employeeDto.getPromotion(),employeeDto.getSalary());
        return resp == Boolean.TRUE ? "success" : "error";
    }
}
