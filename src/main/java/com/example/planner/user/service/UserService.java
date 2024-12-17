package com.example.planner.user.service;

import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import com.example.planner.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto findUserByUserId(String userId);
    void updatePassword(String userId, UserUpdatePasswordRequestDto requestDto);
    void updateUserId(String userId, UserUpdateUserIdRequestDto requestDto);
    void deleteEmail(String userId);
}
