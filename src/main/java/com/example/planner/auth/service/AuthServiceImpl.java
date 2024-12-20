package com.example.planner.auth.service;

import com.example.planner.auth.dto.AuthResponseDto;
import com.example.planner.common.config.PasswordEncoder;
import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.auth.dto.SignupRequestDto;
import com.example.planner.user.entity.User;
import com.example.planner.user.repository.UserRepository;
import com.example.planner.common.util.AuthSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto createUser(SignupRequestDto requestDto) {
        String EncodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = requestDto.toEntity(EncodedPassword);
        if(userRepository.existsByEmail(requestDto.getEmail())){
            throw new AuthenticationException(AuthFailMessage.DUPLICATE_EMAIL);
        }

        userRepository.save(user);
        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logIn(
            LoginRequestDto requestDto,
            HttpSession session
    ) {
        Long existingUserId = getExistingUserId(session);
        if (existingUserId != null) {
            AuthSession.invalidSession(session);
        }

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new AuthenticationException(AuthFailMessage.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException(AuthFailMessage.INVALID_PASSWORD);
        }
        AuthSession.setSession(session, user.getUserId());
        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logOut(HttpSession session) {
        Long sessionUserId = getExistingUserId(session);
        if(sessionUserId == null){
            throw new AuthenticationException(AuthFailMessage.USER_LOGGED_OUT);
        }
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(()->new AuthenticationException(AuthFailMessage.USER_NOT_FOUND));

        AuthSession.invalidSession(session);
        return new AuthResponseDto(user.getEmail(),user.getUserName());
    }

    private Long getExistingUserId(HttpSession session){
        return AuthSession.getSession(session);
    }
}
