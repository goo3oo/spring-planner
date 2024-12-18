package com.example.planner.user.service;

import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import com.example.planner.user.dto.UserResponseDto;

public interface UserService {

    UserResponseDto findUserById(Long id);
    UserResponseDto updatePassword(Long id, Long sessionUserId, UserUpdatePasswordRequestDto requestDto);
    UserResponseDto updateUserName(Long id, Long sessionUserId, UserUpdateUserIdRequestDto requestDto);
    void deleteEmail(Long id, Long sessionUserId);
}
