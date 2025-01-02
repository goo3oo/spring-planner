package com.example.planner.constant.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthFailMessage {
    EMAIL_NOT_FOUND("존재하지 않는 email 입니다."),
    DUPLICATE_EMAIL("이미 등록된 이메일입니다."),
    INVALID_PASSWORD("비밀번호가 올바르지 않습니다."),
    LOGIN_REQUIRED("로그인이 필요합니다."),
    NO_LOGIN_INFO("로그인 상태를 확인할 수 없습니다. 다시 로그인 해 주세요."),
    USER_NOT_FOUND("유효한 로그인 정보가 없습니다."),
    USER_LOGGED_OUT("로그아웃 상태입니다."),
    UNAUTHORIZED_DELETE_ACCESS("삭제 권한이 없습니다"),
    UNAUTHORIZED_UPDATE_ACCESS("수정 권한이 없습니다");

    private final String message;
}
