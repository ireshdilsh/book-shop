package com.example.bookshop.model;

import com.example.bookshop.db.DBConnection;
import com.example.bookshop.dto.PasswordDto;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    // user new account create
    public String createAccount(UserDto userDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into user values (?,?,?)";
        Boolean resp = CrudUtil.executeCrud(sql, userDto.getEmail(),userDto.getUsername(),userDto.getPassword());
        return resp == Boolean.TRUE ? "success" : "fail";
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

    public String changePassword(PasswordDto passwordDto) throws SQLException, ClassNotFoundException {
        String sql = "select count(*) from user where Email = ?";
        String sql1 = "update user set Password = ? where Email = ?";
        ResultSet resultSet = CrudUtil.executeCrud(sql,passwordDto.getEmail());

        while (resultSet.next()){
            Boolean resp = CrudUtil.executeCrud(sql1,passwordDto.getPassword(),passwordDto.getEmail());
            return resp == Boolean.TRUE ? "success" : "fail";
        }
      return null;
    }


    public boolean getEmailByUser(String email) throws SQLException, ClassNotFoundException {
        String sql = "select Email from user where Email=?";
        ResultSet resultSet = CrudUtil.executeCrud(sql,email);

        while (resultSet.next()){
            return true;
        }
        return false;
    }
}
