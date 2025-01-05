package com.example.planner.service.comment;

import com.example.planner.dto.comment.CommentRequestDto;
import com.example.planner.dto.comment.CommentResponseDto;
import java.util.List;
import java.util.Map;

public interface CommentService {

    CommentResponseDto postComment(CommentRequestDto requestDto, Long sessionUserId, Long planId);

    List<CommentResponseDto> findAllCommentByUserId(Long userId);

    List<CommentResponseDto> findAllCommentByPlanId(Long planId);

    CommentResponseDto findCommentById(Long commentId);

    CommentResponseDto updateComment(Long commentId, Long sessionUserId,
        CommentRequestDto requestDto);

    void deleteComment(Long commentId, Long sessionUserId);

    int getCommentCountByPlanId(Long planId);

    List<Object[]> countCommentsByPlanIds(List<Long> planIds);
}
