package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private Date orderDate;
    private int custId;
    private int B_ID;
    private int COU_ID;
    private int DIS_ID;
}
