package com.example.planner.auth.service;

import com.example.planner.auth.dto.AuthResponseDto;
import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.auth.dto.SignupRequestDto;
import com.example.planner.user.entity.User;
import com.example.planner.user.reopository.UserRepository;
import com.example.planner.common.util.AuthSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    @Override
    public AuthResponseDto createUser(SignupRequestDto requestDto) {
        User user = requestDto.toEntity();

        userRepository.save(user);
        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logIn(LoginRequestDto requestDto, HttpSession session) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new AuthenticationException(AuthFailMessage.USER_NOT_FOUND));

        if(!user.getPassword().equals(requestDto.getPassword())){
            throw new AuthenticationException(AuthFailMessage.INVALID_PASSWORD);
        }

        AuthSession.setSession(session, user.getId());
        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logOut(HttpSession session) {
        Long userId = AuthSession.getSession(session);

        User user = userRepository.findById(userId)
                .orElseThrow(()->new AuthenticationException(AuthFailMessage.NO_LOGIN_INFO));

        AuthSession.invalidSession(session);
        return new AuthResponseDto(user.getEmail(),user.getEmail());
    }
}
