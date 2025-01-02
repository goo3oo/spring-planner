package com.example.planner.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserFailMessage {
    USER_NOT_FOUND("유저 정보가 없습니다.");

    private final String message;
}
