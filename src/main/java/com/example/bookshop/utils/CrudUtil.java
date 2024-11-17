package com.example.bookshop.utils;

import com.example.bookshop.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    public static <T>T executeCrud(String querry,Object...obj) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(querry);

        for(int i=0;i<obj.length;i++){
            preparedStatement.setObject(i+1,obj[i]);
        }
        // check querry is executeUpdate or executeQuerry.
        if (querry.startsWith("select") || querry.startsWith("SELECT")){
            ResultSet resultSet = preparedStatement.executeQuery();
            return (T) resultSet;
        }else{
            int i = preparedStatement.executeUpdate();
            boolean isSave = i > 0;
            return (T)((Boolean)isSave);
        }
    }
}
