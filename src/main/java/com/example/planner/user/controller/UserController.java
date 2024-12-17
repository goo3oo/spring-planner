package com.example.planner.user.controller;

import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.user.constant.UserSuccessMessage;
import com.example.planner.user.dto.UserResponseDto;
import com.example.planner.user.exception.UserNotFoundException;
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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> findUserById(@PathVariable Long id){
        try{
            UserResponseDto responseDto = service.findUserById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USER_FIND_SUCCESS.getMessage(),responseDto));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updatePassword(
            @RequestBody UserUpdatePasswordRequestDto requestDto,
            @PathVariable Long id){
        try{
            UserResponseDto responseDto = service.updatePassword(id, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USERNAME_UPDATE_SUCCESS.getMessage(),responseDto));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PatchMapping("/{id}/userId")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUserId(
            @RequestBody UserUpdateUserIdRequestDto requestDto,
            @PathVariable Long id){
        try{
            UserResponseDto responseDto = service.updateUserId(id, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.PASSWORD_UPDATE_SUCCESS.getMessage(),responseDto));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(
            @PathVariable Long id){
        try{
            UserResponseDto responseDto = service.deleteEmail(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USER_DELETE_SUCCESS.getMessage()));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
            }
        }
    }
