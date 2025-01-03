package com.example.planner.service.auth;

import com.example.planner.dto.auth.AuthResponseDto;
import com.example.planner.dto.auth.LoginRequestDto;
import com.example.planner.dto.auth.SignupRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface AuthService {

    AuthResponseDto createUser(SignupRequestDto requestDto);
    AuthResponseDto logIn(LoginRequestDto requestDto, HttpServletRequest request);
    AuthResponseDto logOut(HttpSession session);
}
