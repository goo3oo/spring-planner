package com.example.planner.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanFailMessage {
    PLAN_NOT_FOUND("일정이 존재하지 않습니다.");

    private final String message;
}
