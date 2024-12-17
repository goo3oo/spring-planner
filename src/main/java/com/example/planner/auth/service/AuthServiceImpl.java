package com.example.planner.auth.service;

import com.example.planner.auth.constant.LoginFailMessage;
import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.auth.exception.LoginException;
import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.user.dto.UserRequestDto;
import com.example.planner.user.entity.User;
import com.example.planner.user.reopository.UserRepository;
import com.example.planner.util.AuthSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getUserId(),requestDto.getPassword(),requestDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public ApiResponseDto login(LoginRequestDto requestDto, HttpSession session) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new LoginException(LoginFailMessage.USER_NOT_FOUND));

        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new LoginException(LoginFailMessage.INVALID_PASSWORD);
        }

        AuthSession.setSession(session, user.getId());
        return new ApiResponseDto("로그인 성공",true);
    }
}
