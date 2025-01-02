package com.example.planner.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentListResponseDto {
    private List<CommentResponseDto> comments;
}
