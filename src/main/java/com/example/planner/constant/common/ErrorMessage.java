package com.example.planner.constant.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorMessage {
    // 유효성 검사 관련
    VALIDATION_ERROR("유효성 검사 실패함", HttpStatus.BAD_REQUEST),

    // Not Found 관련
    USER_NOT_FOUND("유저가 존재하지 않음", HttpStatus.NOT_FOUND),
    PLAN_NOT_FOUND("일정이 존재하지 않음", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND("댓글이 존재하지 않음", HttpStatus.NOT_FOUND),

    // 로그인 관련
    LOGIN_REQUIRED("로그인이 필요", HttpStatus.UNAUTHORIZED),
    USER_LOGGED_OUT("로그아웃 상태", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED_ACCESS("접근 권한 없음", HttpStatus.FORBIDDEN),

    // 인증 관련
    EMAIL_NOT_FOUND("존재하지 않는 email", HttpStatus.UNAUTHORIZED),
    INVALID_PASSWORD("비밀번호 불일치", HttpStatus.UNAUTHORIZED),
    DUPLICATE_EMAIL("이미 등록된 이메일", HttpStatus.CONFLICT),
    NO_LOGIN_INFO("세션 만료 또는 로그인 정보 없음", HttpStatus.UNAUTHORIZED);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorMessage(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
