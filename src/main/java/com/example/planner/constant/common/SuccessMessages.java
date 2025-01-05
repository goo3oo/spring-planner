package com.example.planner.constant.common;

import lombok.Getter;

@Getter
public enum SuccessMessages {
    // auth, login 관련 성공 메시지
    SIGN_UP_SUCCESS("회원가입 완료"),
    LOGIN_SUCCESS("로그인 성공"),
    LOGOUT_SUCCESS("로그아웃 완료"),

    // user 관련 성공 메시지
    USER_FIND_SUCCESS("회원정보 조회 완료"),
    USERNAME_UPDATE_SUCCESS("사용자 이름 수정 완료"),
    PASSWORD_UPDATE_SUCCESS("비밀번호 수정 완료"),
    USER_DELETE_SUCCESS("회원정보를 삭제 완료"),

    // plan 관련 성공 메시지
    CREATE_PLAN_SUCCESS("일정 등록 완료"),
    FIND_PLAN_SUCCESS("일정 조회 완료"),
    UPDATE_PLAN_SUCCESS("일정 수정 완료"),
    DELETE_PLAN_SUCCESS("일정 삭제 완료"),

    // comment 관련 성공 메시지
    POST_COMMENT_SUCCESS("댓글 등록 완료"),
    FIND_ALL_COMMENTS_BY_USER_SUCCESS("사용자의 댓글 리스트 조회 완료"),
    FIND_COMMENT_BY_COMMENT_SUCCESS("댓글 조회 완료"),
    FIND_ALL_COMMENTS_BY_PLAN_SUCCESS("일정의 댓글 리스트 조회 완료"),
    UPDATE_COMMENT_SUCCESS("댓글 수정 완료"),
    DELETE_COMMENT_SUCCESS("댓글 삭제 완료");

    private final String message;

    SuccessMessages(String message) {
        this.message = message;
    }
}
