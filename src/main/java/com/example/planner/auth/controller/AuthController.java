package com.example.planner.auth.controller;

import com.example.planner.auth.exception.LoginException;
import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.user.dto.UserRequestDto;
import com.example.planner.auth.service.AuthService;
import com.example.planner.util.AuthSession;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> signUp(
            @RequestBody UserRequestDto requestDto) {
        authService.createUser(requestDto);
        return new ResponseEntity<>(new ApiResponseDto("회원가입이 완료되었습니다.", true), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> logIn(
            @RequestBody LoginRequestDto requestDto, HttpSession session) {
        try {
            ApiResponseDto responseDto = authService.login(requestDto, session);
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }catch (LoginException e){
            return new ResponseEntity<>(new ApiResponseDto(e.getMessage(),false),HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDto> logOut(HttpSession session){
        AuthSession.invalidSession(session);
        return new ResponseEntity<>(new ApiResponseDto("로그아웃 되었습ㄴ다.",true),HttpStatus.OK);
    }
}
