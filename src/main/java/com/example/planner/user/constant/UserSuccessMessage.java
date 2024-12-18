package com.example.planner.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserSuccessMessage {
    USER_FIND_SUCCESS("회원정보를 조회했습니다."),
    USERNAME_UPDATE_SUCCESS("사용자 이름을 수정했습니다."),
    PASSWORD_UPDATE_SUCCESS("비밀번호를 수정했습니다."),
    USER_DELETE_SUCCESS("회원정보를 삭제했습니다.");

    private final String message;

}
