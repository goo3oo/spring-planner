package com.example.planner.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentSuccessMessage {
    POST_COMMENT_SUCCESS("댓글이 등록되었습니다."),
    FIND_ALL_COMMENTS_BY_USER_SUCCESS("사용자의 댓글이 모두 조회되었습니다."),
    FIND_COMMENT_BY_COMMENT_SUCCESS("댓글이 조회되었습니다."),
    FIND_ALL_COMMENTS_BY_PLAN_SUCCESS("일정의 댓글이 모두 조회되었습니다."),
    UPDATE_COMMENT_SUCCESS("댓글이 수정되었습니다."),
    DELETE_COMMENT_SUCCESS("댓글이 삭제되었습니다.");

    private final String message;
}
