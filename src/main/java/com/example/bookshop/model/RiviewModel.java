package com.example.bookshop.model;

import com.example.bookshop.dto.RiviewDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RiviewModel {
    public String addNewRiview(RiviewDto riviewDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into riview(Discription,custName) values(?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,riviewDto.getDiscription(),riviewDto.getCustName());
        return resp == Boolean.TRUE ? "success" : "error";
    }

    public ArrayList<RiviewDto> getAllRiviews() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Discription,custName FROM riview";
        ArrayList<RiviewDto> riviews = new ArrayList<>();
        try {
            ResultSet resultSet = CrudUtil.executeCrud(sql);
            while (resultSet.next()) {
                riviews.add(new RiviewDto(
                        //resultSet.getInt("RIV_ID"),
                        resultSet.getString("Discription"),
                        resultSet.getString("custName")));
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } return riviews;
    }

    public ArrayList<RiviewDto> getAllRiviewsTable() throws SQLException, ClassNotFoundException {
        String sql = "select Discription,custName from riview";
        ResultSet resultSet = CrudUtil.executeCrud(sql);
        ArrayList<RiviewDto> riviews = new ArrayList<>();

        while (resultSet.next()) {
               RiviewDto riviewDto = new RiviewDto(
                     resultSet.getString(2),resultSet.getString(1)
               );
               riviews.add(riviewDto);
        }
        return riviews;
    }
}
