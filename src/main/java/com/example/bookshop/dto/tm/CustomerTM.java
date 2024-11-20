package com.example.bookshop.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTM {
    private String Name;
    private String Address;
    private int PhoneNumber;
}
