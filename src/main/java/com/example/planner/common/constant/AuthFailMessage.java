package com.example.planner.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthFailMessage {

    EMAIL_NOT_FOUND("존재하지 않는 email 입니다."),
    INVALID_PASSWORD("비밀번호가 올바르지 않습니다."),
    LOGIN_REQUIRED("로그인이 필요합니다."),
    NO_LOGIN_INFO("로그인 상태를 확인할 수 없습니다. 다시 로그인 해 주세요."),
    USER_NOT_FOUND("유효한 로그인 정보가 없습니다."),
    USER_LOGGED_OUT("로그아웃 상태입니다.");

    private final String message;
}
