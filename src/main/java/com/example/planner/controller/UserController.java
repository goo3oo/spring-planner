package com.example.planner.controller;

import com.example.planner.dto.ApiResponseDto;
import com.example.planner.dto.UserRequestDto;
import com.example.planner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody UserRequestDto requestDto){
        service.createUser(requestDto);
        return new ResponseEntity<>(new ApiResponseDto("사용자 등록이 완료되었습니다.",true), HttpStatus.CREATED);
    }
}
