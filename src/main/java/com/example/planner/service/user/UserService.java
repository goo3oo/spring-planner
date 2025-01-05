package com.example.planner.service.user;

import com.example.planner.dto.user.UserUpdateUserIdRequestDto;
import com.example.planner.dto.user.UserUpdatePasswordRequestDto;
import com.example.planner.dto.user.UserResponseDto;
import com.example.planner.model.User;

import java.util.List;

public interface UserService {

    UserResponseDto findUserById(Long id);
    List<UserResponseDto> findAllUser();
    UserResponseDto updatePassword(Long id, Long sessionUserId, UserUpdatePasswordRequestDto requestDto);
    UserResponseDto updateUserName(Long id, Long sessionUserId, UserUpdateUserIdRequestDto requestDto);
    void deleteUser(Long id, Long sessionUserId);
    void checkDuplicateEmail(String email);
    User findUserByEmailOrThrow(String email);
    User findUserByUserIdOrThrow(Long sessionUserId);
    void saveUser(User user);
    User findById(Long sessionUserId);
    boolean existsByUserId(Long userId);
}
