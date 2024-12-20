package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {
    private String empName;
    private int contact;
    private int age;
    private int promotion;
    private int salary;

    public EmployeeDto(int anInt, int anInt1) {
        this.age = anInt;
        this.contact = anInt1;
    }
}
