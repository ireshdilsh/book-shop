package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String username;
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
