package com.example.planner.controller.auth;

import com.example.planner.dto.auth.AuthResponseDto;
import com.example.planner.constant.common.AuthSuccessMessage;
import com.example.planner.dto.common.ValidationResponseDto;
import com.example.planner.exception.AuthenticationException;
import com.example.planner.dto.common.ApiResponseDto;
import com.example.planner.dto.auth.LoginRequestDto;
import com.example.planner.dto.auth.SignupRequestDto;
import com.example.planner.service.auth.AuthService;
import com.example.planner.util.BindingResultUtils;
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
            return ResponseEntity.status(HttpStatus.CONFLICT)
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }
}
