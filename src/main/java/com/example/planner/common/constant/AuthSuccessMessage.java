package com.example.planner.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthSuccessMessage {

    SIGN_UP_SUCCESS("회원가입이 완료되었습니다."),
    LOGIN_SUCCESS("로그인 되었습니다."),
    LOGOUT_SUCCESS("로그아웃 되었습니다.");

    private final String message;
}
