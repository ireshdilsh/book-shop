package com.example.bookshop.model;

import com.example.bookshop.db.DBConnection;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    public String createAccount(UserDto userDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into user values (?,?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,userDto.getEmail(),userDto.getUsername(),userDto.getPassword());
        return resp == Boolean.TRUE ? "Account created Successfully !" : "Somthing Went Wrong !";
    }

    // user login validation
    public String loginAuthentication(UserDto userDto) throws SQLException, ClassNotFoundException {
        String sql = "select Email,Password from user where Email=? and Password=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, userDto.getEmail());
        preparedStatement.setString(2, userDto.getPassword());

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return "Login Successfully.";
        } else {
            return "Invalid Email or Password. Try again !";
        }
    }
}
