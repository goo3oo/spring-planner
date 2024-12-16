package com.example.planner.service;

import com.example.planner.dto.UserRequestDto;
import com.example.planner.dto.UserResponseDto;
import com.example.planner.dto.UserUpdateEmailRequestDto;
import com.example.planner.dto.UserUpdatePasswordRequestDto;

public interface UserService {
    void createUser(UserRequestDto requestDto);
    UserResponseDto findUserByUserId(String userId);
    void updatePassword(String userId, UserUpdatePasswordRequestDto requestDto);
    void updateEmail(String userId, UserUpdateEmailRequestDto requestDto);
}
