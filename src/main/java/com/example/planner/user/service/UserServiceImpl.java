package com.example.planner.user.service;

import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import com.example.planner.user.dto.UserResponseDto;
import com.example.planner.user.entity.User;
import com.example.planner.user.reopository.UserRepository;
import com.example.planner.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserResponseDto findUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"해당하는 사용자가 없습니다."));
        return UserMapper.UserToDto(user);
    }

    @Override
    @Transactional
    public void updatePassword(String userId, UserUpdatePasswordRequestDto requestDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"해당하는 사용자가 없습니다."));
        user.updatePassword(requestDto.getPassword());
    }

    @Override
    @Transactional
    public void updateUserId(String userId, UserUpdateUserIdRequestDto requestDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"해당하는 사용자가 없습니다."));
        user.updateUserId(requestDto.getUserId());
    }

    @Override
    public void deleteEmail(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"해당하는 사용자가 없습니다."));
        userRepository.delete(user);
    }

}
