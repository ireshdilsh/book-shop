package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDto {
    private String bookName;
    private String categoryName;
    private double price;
    private String suplierName;
    private int qty;
    private ArrayList<BookDetailsDto> bookDetailsDtos;

    public BookDto(String bookName, String categoryName, double price ){
        this.bookName = bookName;
        this.categoryName = categoryName;
        this.price = price;
    }
}
