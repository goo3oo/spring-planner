package com.example.planner.service.auth;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.dto.auth.AuthResponseDto;
import com.example.planner.config.PasswordEncoder;
import com.example.planner.dto.auth.LoginRequestDto;
import com.example.planner.dto.auth.SignupRequestDto;
import com.example.planner.exception.LoginException;
import com.example.planner.model.User;
import com.example.planner.service.user.UserService;
import com.example.planner.util.AuthSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService{

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDto createUser(SignupRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        User user = requestDto.toEntity(encodedPassword);
        // 이메일 중복 체크
        userService.checkDuplicateEmail(requestDto.getEmail());

        userService.saveUser(user);
        return new AuthResponseDto(user.getEmail(), user.getUserName(   ));
    }

    @Override
    public AuthResponseDto logIn(LoginRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long existingUserId = AuthSession.getSession(session);
        // 로그인 상태면 로그아웃
        if (existingUserId != null) {
            AuthSession.invalidSession(request.getSession());
            session = request.getSession(true);
        }

        User user = userService.findUserByEmailOrThrow(requestDto.getEmail());
        // 패스워드 일치 여부 확인 ( User entity 에 로직 위임 )
        user.validatePassword(passwordEncoder, requestDto.getPassword());

        AuthSession.setSession(session, user.getUserId());

        return new AuthResponseDto(user.getEmail(), user.getUserName());
    }

    @Override
    public AuthResponseDto logOut(HttpSession session) {
        Long sessionUserId = AuthSession.getSession(session);
        // 로그인 상태인지 확인
        if(sessionUserId == null){
            throw new LoginException(ErrorMessage.USER_LOGGED_OUT);
        }

        User user = userService.findUserByUserIdOrThrow(sessionUserId);

        AuthSession.invalidSession(session);

        return new AuthResponseDto(user.getEmail(),user.getUserName());
    }
}
