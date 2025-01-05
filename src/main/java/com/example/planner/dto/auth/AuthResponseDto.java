package com.example.planner.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthResponseDto {

    private String email;
    private String userName;
}
