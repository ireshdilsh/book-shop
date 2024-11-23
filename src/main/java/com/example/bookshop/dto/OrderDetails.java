package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {
    private int bookID;
    private int orderID;
    private Double price;
    private int quantity;

    public OrderDetails(int anInt, double aDouble, int anInt1) {
        this.bookID = anInt;
        this.price = aDouble;
        this.orderID = anInt1;
    }
}
