package com.example.planner.controller;

import com.example.planner.dto.*;
import com.example.planner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserByUserId(@PathVariable String userId){
        return new ResponseEntity<>(service.findUserByUserId(userId),HttpStatus.OK);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<ApiResponseDto> updatePassword(
            @RequestBody UserUpdatePasswordRequestDto requestDto,
            @PathVariable String userId){
        service.updatePassword(userId, requestDto);
        return new ResponseEntity<>(new ApiResponseDto("사용자 정보가 수정되었습니다.",true),HttpStatus.OK);
    }

    @PatchMapping("/{userId}/email")
    public ResponseEntity<ApiResponseDto> updateEmail(
            @RequestBody UserUpdateEmailRequestDto requestDto,
            @PathVariable String userId){
        service.updateEmail(userId, requestDto);
        return new ResponseEntity<>(new ApiResponseDto("사용자 정보가 수정되었습니다.",true),HttpStatus.OK);
    }
}
