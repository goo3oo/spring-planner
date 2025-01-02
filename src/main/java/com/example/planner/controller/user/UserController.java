package com.example.planner.controller.user;

import com.example.planner.dto.common.ApiResponseDto;
import com.example.planner.dto.common.ValidationResponseDto;
import com.example.planner.exception.AuthenticationException;
import com.example.planner.util.AuthSession;
import com.example.planner.util.BindingResultUtils;
import com.example.planner.constant.UserSuccessMessage;
import com.example.planner.dto.user.UserListResponseDto;
import com.example.planner.dto.user.UserResponseDto;
import com.example.planner.exception.UserNotFoundException;
import com.example.planner.service.user.UserService;
import com.example.planner.dto.user.UserUpdateUserIdRequestDto;
import com.example.planner.dto.user.UserUpdatePasswordRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> findUserById(@PathVariable Long id) {
        try {
            UserResponseDto responseDto = service.findUserById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USER_FIND_SUCCESS.getMessage(), responseDto));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<UserListResponseDto>> findAllUser(){
        try {
            UserListResponseDto responseDto = new UserListResponseDto(service.findAllUser());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USER_FIND_SUCCESS.getMessage(),responseDto));
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(
            @Valid @RequestBody UserUpdatePasswordRequestDto requestDto,
            BindingResult bindingResult,
            @PathVariable Long id,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }

        try {
            UserResponseDto responseDto = service.updatePassword(id, sessionUserId, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.PASSWORD_UPDATE_SUCCESS.getMessage(), responseDto));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PatchMapping("/{id}/userName")
    public ResponseEntity<?> updateUserName(
            @Valid @RequestBody UserUpdateUserIdRequestDto requestDto,
            BindingResult bindingResult,
            @PathVariable Long id,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }

        try {
            UserResponseDto responseDto = service.updateUserName(id, sessionUserId, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USERNAME_UPDATE_SUCCESS.getMessage(), responseDto));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(
            @PathVariable Long id,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        try {
            service.deleteEmail(id, sessionUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(UserSuccessMessage.USER_DELETE_SUCCESS.getMessage()));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }
}
