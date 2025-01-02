package com.example.planner.service.auth;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.dto.auth.AuthResponseDto;
import com.example.planner.config.PasswordEncoder;
import com.example.planner.dto.auth.LoginRequestDto;
import com.example.planner.exception.AuthenticationException;
import com.example.planner.dto.auth.SignupRequestDto;
import com.example.planner.exception.LoginException;
import com.example.planner.exception.UserNotFoundException;
import com.example.planner.model.User;
import com.example.planner.repository.user.UserRepository;
import com.example.planner.util.AuthSession;
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
            throw new AuthenticationException(ErrorMessage.DUPLICATE_EMAIL);
        }

        userRepository.save(user);
        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logIn(LoginRequestDto requestDto, HttpSession session) {

        Long existingUserId = getExistingUserId(session);
        if (existingUserId != null) {
            AuthSession.invalidSession(session);
        }

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new AuthenticationException(ErrorMessage.EMAIL_NOT_FOUND));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException(ErrorMessage.INVALID_PASSWORD);
        }
        AuthSession.setSession(session, user.getUserId());
        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logOut(HttpSession session) {
        Long sessionUserId = getExistingUserId(session);
        if(sessionUserId == null){
            throw new LoginException(ErrorMessage.USER_LOGGED_OUT);
        }
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(()->new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

        AuthSession.invalidSession(session);
        return new AuthResponseDto(user.getEmail(),user.getUserName());
    }

    private Long getExistingUserId(HttpSession session){
        return AuthSession.getSession(session);
    }
}
