package com.example.planner.common.util;

import com.example.planner.comment.dto.CommentResponseDto;
import com.example.planner.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {
    public static CommentResponseDto toDto(Comment comment){
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getPlan().getPlanId(),
                comment.getUser().getEmail(),
                comment.getUser().getUserName(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
