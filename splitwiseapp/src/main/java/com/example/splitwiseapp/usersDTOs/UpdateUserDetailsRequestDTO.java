package com.example.splitwiseapp.usersDTOs;

import lombok.Data;

@Data
public class UpdateUserDetailsRequestDTO {
    private String userName;
    private String email;
    private String password;
}
