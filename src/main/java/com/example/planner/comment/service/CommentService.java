package com.example.planner.comment.service;

import com.example.planner.comment.dto.CommentListResponseDto;
import com.example.planner.comment.dto.CommentRequestDto;
import com.example.planner.comment.dto.CommentResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface CommentService {

    CommentResponseDto postComment(CommentRequestDto requestDto, Long sessionUserId, Long planId);
    List<CommentResponseDto> findAllCommentByUserId(Long userId);
    List<CommentResponseDto> findAllCommentByPlanId(Long planId);
    CommentResponseDto findCommentById(Long commentId);
    void deleteComment(Long commentId);

    CommentResponseDto updateComment(Long commentId);
}
