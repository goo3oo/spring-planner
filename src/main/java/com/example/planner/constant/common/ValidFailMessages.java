package com.example.planner.constant.common;

import lombok.Getter;

@Getter
public class ValidFailMessages {

    // 유효성 검사 실패 메시지
    public static final String NOT_NULL = "필수 값이 없음";
    public static final String INVALID_EMAIL_FORMAT = "잘못된 이메일 형식";
    public static final String PASSWORD_LENGTH = "비밀번호는 4~20자 내로 입력";
    public static final String USERNAME_LENGTH = "유저네임은 3~20자 내로 입력";
    public static final String COMMENT_LENGTH = "댓글은 1~500자 내로 입력";
    public static final String PLAN_TITLE_LENGTH = "일정 제목은 1~50자 내로 입력";
    public static final String PLAN_CONTENT_LENGTH = "일정 내용은 1000자 내로 입력";
}
