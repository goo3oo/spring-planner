package com.example.planner.comment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentFailMessage {
    COMMENT_NOT_FOUND("댓글이 존재하지 않습니다.");

    private final String message;
}
