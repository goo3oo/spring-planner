package com.example.planner.auth.service;

import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.user.dto.UserRequestDto;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    void createUser(UserRequestDto requestDto);
    ApiResponseDto login(LoginRequestDto requestDto, HttpSession session);
}
