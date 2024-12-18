package com.example.planner.common.util;

import com.example.planner.comment.dto.CommentResponseDto;
import com.example.planner.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {
    public static CommentResponseDto toDto(Comment comment){
        String userName = (comment.getUser() != null) ? comment.getUser().getUserName() : "사용자 삭제됨";
        String email = (comment.getUser() != null) ? comment.getUser().getEmail() : "사용자 삭제됨";

        return new CommentResponseDto(
                comment.getId(),
                email,
                userName,
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
