package com.example.bookshop.dto;

import com.example.bookshop.utils.CrudUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private String custName;
    private String custAddress;
    private int custPhone;

    public CustomerDto(String string, int anInt) {
        this.custAddress = string;
        this.custPhone = anInt;
    }

}
