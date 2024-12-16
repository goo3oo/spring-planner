package com.example.planner.controller;

import com.example.planner.dto.ApiResponseDto;
import com.example.planner.dto.UserRequestDto;
import com.example.planner.service.AuthService;
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
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        authService.createUser(requestDto);
        return new ResponseEntity<>(new ApiResponseDto("회원가입이 완료되었습니다.",true), HttpStatus.CREATED);
    }


}
