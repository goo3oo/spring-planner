package com.example.planner.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CommentResponseDto {
    private Long id;
    private String email;
    private String userName;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
