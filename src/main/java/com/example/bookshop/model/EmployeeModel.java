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

    public ArrayList<String> getAllEmployeeName() throws SQLException, ClassNotFoundException {
        String sql = "select Name from employee";
        ResultSet resultSet = CrudUtil.executeCrud(sql);
        ArrayList<String> employeeName = new ArrayList<>();

        while (resultSet.next()) {
            employeeName.add(resultSet.getString(1));
        }
        return employeeName;
    }

    public String deleteEmployee(String name) throws SQLException, ClassNotFoundException {
        String sql = "delete from employee where Name = ?";
        Boolean resp = CrudUtil.executeCrud(sql,name);
        return resp == Boolean.TRUE ? "success" : "fail";
    }

    public EmployeeDto findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "select Age,Contact from employee where Name = ?";
        ResultSet resultSet = CrudUtil.executeCrud(sql,name);

        if (resultSet.next()){
            return new EmployeeDto(
              resultSet.getInt(1),resultSet.getInt(2)
            );
        }
        return null;
    }

    public String updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        String sql = "update employee set Contact = ? ,Age = ?, PRO_ID = ?, SAL_ID = ? where Name = ?";
        Boolean resp = CrudUtil.executeCrud(sql,employeeDto.getContact(),employeeDto.getAge(),employeeDto.getPromotion(),employeeDto.getSalary(),employeeDto.getEmpName());
        return resp == Boolean.TRUE ? "success" : "fail";
    }
}
