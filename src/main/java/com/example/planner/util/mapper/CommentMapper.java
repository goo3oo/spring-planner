package com.example.planner.util.mapper;

import com.example.planner.dto.comment.CommentResponseDto;
import com.example.planner.model.Comment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static CommentResponseDto toDto(Comment comment) {
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
