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

    public BookDto(String string, String string1, double aDouble, ArrayList<BookDetailsDto> suppliers) {
        this.bookName = string;
        this.categoryName = string1;
        this.price = aDouble;
        this.bookDetailsDtos = suppliers;
    }
}
