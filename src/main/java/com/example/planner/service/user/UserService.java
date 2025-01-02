package com.example.planner.service.user;

import com.example.planner.dto.user.UserUpdateUserIdRequestDto;
import com.example.planner.dto.user.UserUpdatePasswordRequestDto;
import com.example.planner.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto findUserById(Long id);
    List<UserResponseDto> findAllUser();
    UserResponseDto updatePassword(Long id, Long sessionUserId, UserUpdatePasswordRequestDto requestDto);
    UserResponseDto updateUserName(Long id, Long sessionUserId, UserUpdateUserIdRequestDto requestDto);
    void deleteEmail(Long id, Long sessionUserId);
}
