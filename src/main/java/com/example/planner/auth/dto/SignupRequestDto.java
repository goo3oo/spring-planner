package com.example.planner.auth.dto;


import com.example.planner.user.entity.User;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String email;
    private String userName;
    private String password;

    public User toEntity(){
        return new User(email, userName, password);
    }
}
