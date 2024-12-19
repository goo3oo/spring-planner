package com.example.planner.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private Long commentId;
    private Long planId;
    private String email;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
