package com.example.planner.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentListResponseDto {
    private List<CommentResponseDto> comments;
}
