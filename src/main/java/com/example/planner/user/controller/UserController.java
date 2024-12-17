package com.example.planner.user.controller;

import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.user.dto.UserResponseDto;
import com.example.planner.user.service.UserService;
import com.example.planner.user.dto.UserUpdateUserIdRequestDto;
import com.example.planner.user.dto.UserUpdatePasswordRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService service;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserByUserId(@PathVariable String userId){
        return new ResponseEntity<>(service.findUserByUserId(userId),HttpStatus.OK);
    }

    @PatchMapping("/{userId}/password")
    public ResponseEntity<ApiResponseDto> updatePassword(
            @RequestBody UserUpdatePasswordRequestDto requestDto,
            @PathVariable String userId){
        service.updatePassword(userId, requestDto);
        return new ResponseEntity<>(new ApiResponseDto("비밀번호가 수정되었습니다.",true),HttpStatus.OK);
    }

    @PatchMapping("/{userId}/userId")
    public ResponseEntity<ApiResponseDto> updateUserId(
            @RequestBody UserUpdateUserIdRequestDto requestDto,
            @PathVariable String userId){
        service.updateUserId(userId, requestDto);
        return new ResponseEntity<>(new ApiResponseDto("사용자 이름이 수정되었습니다.",true),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseDto> deleteUser(
            @PathVariable String userId){
        service.deleteEmail(userId);
        return new ResponseEntity<>(new ApiResponseDto("사용자 정보가 삭제되었습니다.",true),HttpStatus.OK);
    }
}
