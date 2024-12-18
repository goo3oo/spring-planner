package com.example.planner.user.service;

import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import com.example.planner.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto findUserById(Long id);
    UserResponseDto updatePassword(Long id, UserUpdatePasswordRequestDto requestDto);
    UserResponseDto updateUserName(Long id, UserUpdateUserIdRequestDto requestDto);
    void deleteEmail(Long id);
}
