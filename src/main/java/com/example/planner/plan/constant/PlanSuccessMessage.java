package com.example.planner.plan.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanSuccessMessage {
    CREATE_PLAN_SUCCESS("일정이 등록되었습니다."),
    FIND_PLAN_SUCCESS("일정이 조회되었습니다."),
    UPDATE_PLAN_SUCCESS("일정이 수정되었습니다."),
    DELETE_PLAN_SUCCESS("일정이 삭제되었습니다.");

    private final String message;

}
