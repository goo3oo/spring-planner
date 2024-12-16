package com.example.planner.service;

import com.example.planner.dto.UserRequestDto;
import com.example.planner.entity.User;
import com.example.planner.reopository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUserId(),requestDto.getPassword(),requestDto.getEmail());
        userRepository.save(user);
    }

}
