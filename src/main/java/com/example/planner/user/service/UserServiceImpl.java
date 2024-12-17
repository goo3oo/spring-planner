package com.example.planner.user.service;

import com.example.planner.user.constant.UserFailMessage;
import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import com.example.planner.user.dto.UserResponseDto;
import com.example.planner.user.entity.User;
import com.example.planner.user.exception.UserNotFoundException;
import com.example.planner.user.reopository.UserRepository;
import com.example.planner.common.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));

        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto updatePassword(Long id, UserUpdatePasswordRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));
        user.updatePassword(requestDto.getPassword());
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUserId(Long id, UserUpdateUserIdRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));
        user.updateUserName(requestDto.getUserName());
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto deleteEmail(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));
        userRepository.delete(user);
        return UserMapper.toDto(user);
    }
}
