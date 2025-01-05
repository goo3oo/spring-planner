package com.example.planner.controller.user;

import com.example.planner.constant.common.SuccessMessages;
import com.example.planner.dto.common.SuccessResponseDto;
import com.example.planner.util.AuthSession;
import com.example.planner.dto.user.UserListResponseDto;
import com.example.planner.dto.user.UserResponseDto;
import com.example.planner.service.user.UserService;
import com.example.planner.dto.user.UserUpdateUserIdRequestDto;
import com.example.planner.dto.user.UserUpdatePasswordRequestDto;
import jakarta.validation.Valid;
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
    public ResponseEntity<SuccessResponseDto<UserResponseDto>> findUserById(@PathVariable Long id) {
        UserResponseDto responseDto = service.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                SuccessResponseDto.of(SuccessMessages.USER_FIND_SUCCESS.getMessage(), responseDto));
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDto<UserListResponseDto>> findAllUser() {
        UserListResponseDto responseDto = new UserListResponseDto(service.findAllUser());
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                SuccessResponseDto.of(SuccessMessages.USER_FIND_SUCCESS.getMessage(), responseDto));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<SuccessResponseDto<UserResponseDto>> updatePassword(
        @Valid @RequestBody UserUpdatePasswordRequestDto requestDto,
        @PathVariable Long id,
        @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        UserResponseDto responseDto = service.updatePassword(id, sessionUserId, requestDto);
        return ResponseEntity.status(HttpStatus.OK)
            .body(SuccessResponseDto.of(SuccessMessages.PASSWORD_UPDATE_SUCCESS.getMessage(),
                responseDto));
    }

    @PatchMapping("/{id}/userName")
    public ResponseEntity<SuccessResponseDto<UserResponseDto>> updateUserName(
        @Valid @RequestBody UserUpdateUserIdRequestDto requestDto,
        @PathVariable Long id,
        @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        UserResponseDto responseDto = service.updateUserName(id, sessionUserId, requestDto);
        return ResponseEntity.status(HttpStatus.OK)
            .body(SuccessResponseDto.of(SuccessMessages.USERNAME_UPDATE_SUCCESS.getMessage(),
                responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDto<Void>> deleteUser(
        @PathVariable Long id,
        @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        service.deleteUser(id, sessionUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
