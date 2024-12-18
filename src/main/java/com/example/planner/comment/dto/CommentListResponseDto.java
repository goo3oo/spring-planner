package com.example.planner.comment.dto;

import com.example.planner.plan.dto.PlanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

public class CommentListResponseDto {
    private List<CommentResponseDto> comments;
}
