package com.example.planner.user.service;

import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.user.constant.UserFailMessage;
import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import com.example.planner.user.dto.UserResponseDto;
import com.example.planner.user.entity.User;
import com.example.planner.user.exception.UserNotFoundException;
import com.example.planner.user.repository.UserRepository;
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
    public UserResponseDto updatePassword(Long id, Long sessionUserId, UserUpdatePasswordRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));

        if (!user.getId().equals(sessionUserId)) {
            throw new AuthenticationException(AuthFailMessage.UNAUTHORIZED_UPDATE_ACCESS);
        }

        user.updatePassword(requestDto.getPassword());
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUserName(Long id, Long sessionUserId, UserUpdateUserIdRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));

        if (!user.getId().equals(sessionUserId)) {
            throw new AuthenticationException(AuthFailMessage.UNAUTHORIZED_UPDATE_ACCESS);
        }

        user.updateUserName(requestDto.getUserName());
        return UserMapper.toDto(user);
    }

    @Override
    public void deleteEmail(Long id, Long sessionUserId) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(UserFailMessage.USER_NOT_FOUND));

        if (!user.getId().equals(sessionUserId)) {
            throw new AuthenticationException(AuthFailMessage.UNAUTHORIZED_UPDATE_ACCESS);
        }

        userRepository.delete(user);
    }
}
