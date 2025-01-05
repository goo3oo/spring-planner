package com.example.planner.controller.auth;

import com.example.planner.constant.common.SuccessMessages;
import com.example.planner.dto.auth.AuthResponseDto;

import com.example.planner.dto.auth.LoginRequestDto;
import com.example.planner.dto.auth.SignupRequestDto;
import com.example.planner.dto.common.SuccessResponseDto;
import com.example.planner.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    public ResponseEntity<SuccessResponseDto<AuthResponseDto>> signUp(
        @Valid @RequestBody SignupRequestDto requestDto
    ) {
        AuthResponseDto responseDto = authService.createUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).
            body(SuccessResponseDto.of(SuccessMessages.SIGN_UP_SUCCESS.getMessage(), responseDto));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponseDto<AuthResponseDto>> logIn(
        @Valid @RequestBody LoginRequestDto requestDto,
        HttpServletRequest request
    ) {
        AuthResponseDto responseDto = authService.logIn(requestDto, request);
        return ResponseEntity.status(HttpStatus.OK).
            body(SuccessResponseDto.of(SuccessMessages.LOGIN_SUCCESS.getMessage(), responseDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<SuccessResponseDto<AuthResponseDto>> logOut(HttpSession session) {

        AuthResponseDto responseDto = authService.logOut(session);
        return ResponseEntity.status(HttpStatus.OK).
            body(SuccessResponseDto.of(SuccessMessages.LOGOUT_SUCCESS.getMessage(), responseDto));
    }
}
