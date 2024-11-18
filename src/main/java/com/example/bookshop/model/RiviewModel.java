package com.example.bookshop.model;

import com.example.bookshop.dto.RiviewDto;
import com.example.bookshop.utils.CrudUtil;

import java.sql.SQLException;

public class RiviewModel {
    public String addNewRiview(RiviewDto riviewDto) throws SQLException, ClassNotFoundException {
        String sql = "insert into riview(Discription,custName) values(?,?)";
        Boolean resp = CrudUtil.executeCrud(sql,riviewDto.getDiscription(),riviewDto.getCustName());
        return resp == Boolean.TRUE ? "success" : "error";
    }
}
