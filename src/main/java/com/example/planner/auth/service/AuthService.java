package com.example.planner.auth.service;

import com.example.planner.auth.dto.AuthResponseDto;
import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.auth.dto.SignupRequestDto;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    AuthResponseDto createUser(SignupRequestDto requestDto);
    AuthResponseDto logIn(LoginRequestDto requestDto, HttpSession session);
    AuthResponseDto logOut(HttpSession session);
}
