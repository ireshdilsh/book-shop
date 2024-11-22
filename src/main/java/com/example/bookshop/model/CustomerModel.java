package com.example.bookshop.model;

import com.example.bookshop.dto.CustomerDto;
import com.example.bookshop.dto.RiviewDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return null;
    }

    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "select Name,Address,Contact from customer";
        ResultSet rst = CrudUtil.executeCrud(sql);

        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            CustomerDto customerDTO = new CustomerDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3)
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public ArrayList<String> getAllCustomerName() throws SQLException, ClassNotFoundException {
        String sql = "select Name from customer";
        ArrayList<String> customerName = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        while (resultSet.next()) {
            customerName.add(resultSet.getString(1));
        }
        return customerName;
    }

    public CustomerDto findByName(String name) throws SQLException, ClassNotFoundException {
        String sql = "select Address,Contact from customer where Name = ? ";
        ResultSet resultSet = CrudUtil.executeCrud(sql,name);

        if (resultSet.next()) {
            return new CustomerDto(
              resultSet.getString(1),resultSet.getInt(2)
            );
        }
        return null;
    }
    public String updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        String sql = "update customer set Address = ? , Contact = ? where Name = ?";
        Boolean resp = CrudUtil.executeCrud(sql,customerDto.getCustAddress(),customerDto.getCustPhone(),customerDto.getCustName());
        return resp == Boolean.TRUE ? "success" : "error";
    }

    public String deleteCustomer(String name) throws SQLException, ClassNotFoundException {
        String sql = "delete from customer where Name = ?";
        Boolean resp = CrudUtil.executeCrud(sql,name);
        return resp == Boolean.TRUE ? "success" : "error";
    }

    public ArrayList<String> getAllBookName() throws SQLException, ClassNotFoundException {
        String sql = "select Name from book";
        ArrayList<String> bookName = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeCrud(sql);

        while (resultSet.next()) {
            bookName.add(resultSet.getString(1));
        }
        return bookName;
    }
}
