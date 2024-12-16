package com.example.planner.service;

import com.example.planner.dto.UserRequestDto;
import com.example.planner.dto.UserResponseDto;

public interface UserService {
    void createUser(UserRequestDto requestDto);
    UserResponseDto findUserByUserId(String userId);
}
