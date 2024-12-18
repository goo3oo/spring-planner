package com.example.planner.auth.controller;

import com.example.planner.auth.dto.AuthResponseDto;
import com.example.planner.common.constant.AuthSuccessMessage;
import com.example.planner.common.dto.ValidationResponseDto;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.auth.dto.LoginRequestDto;
import com.example.planner.auth.dto.SignupRequestDto;
import com.example.planner.auth.service.AuthService;
import com.example.planner.common.util.BindingResultUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> signUp(
            @Valid @RequestBody SignupRequestDto requestDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }
        try{
            AuthResponseDto responseDto = authService.createUser(requestDto);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponseDto.success(AuthSuccessMessage.SIGN_UP_SUCCESS.getMessage(), responseDto));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(
            @Valid @RequestBody LoginRequestDto requestDto,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }

        try {
            AuthResponseDto responseDto = authService.logIn(requestDto, session);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(AuthSuccessMessage.LOGIN_SUCCESS.getMessage(), responseDto));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> logOut(HttpSession session) {
        try {
            AuthResponseDto responseDto = authService.logOut(session);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(AuthSuccessMessage.LOGOUT_SUCCESS.getMessage(), responseDto));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }
}
