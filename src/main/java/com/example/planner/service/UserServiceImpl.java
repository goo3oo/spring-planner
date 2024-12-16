package com.example.planner.service;

import com.example.planner.dto.UserRequestDto;
import com.example.planner.dto.UserResponseDto;
import com.example.planner.entity.User;
import com.example.planner.reopository.UserRepository;
import com.example.planner.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUserId(),requestDto.getPassword(),requestDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public UserResponseDto findUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return UserMapper.UserToDto(user);
    }

}
