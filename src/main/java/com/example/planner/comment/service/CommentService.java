package com.example.planner.comment.service;

import com.example.planner.comment.dto.CommentRequestDto;
import com.example.planner.comment.dto.CommentResponseDto;
import java.util.List;

public interface CommentService {
    CommentResponseDto postComment(CommentRequestDto requestDto, Long sessionUserId, Long planId);
    List<CommentResponseDto> findAllCommentByUserId(Long userId);
    List<CommentResponseDto> findAllCommentByPlanId(Long planId);
    CommentResponseDto findCommentById(Long commentId);
    CommentResponseDto updateComment(Long commentId, Long sessionUserId, CommentRequestDto requestDto);
    void deleteComment(Long commentId, Long sessionUserId);
}
