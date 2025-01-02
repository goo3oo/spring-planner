package com.example.planner.service.comment;

import com.example.planner.dto.comment.CommentRequestDto;
import com.example.planner.dto.comment.CommentResponseDto;
import java.util.List;

public interface CommentService {

    CommentResponseDto postComment(CommentRequestDto requestDto, Long sessionUserId, Long planId);
    List<CommentResponseDto> findAllCommentByUserId(Long userId);
    List<CommentResponseDto> findAllCommentByPlanId(Long planId);
    CommentResponseDto findCommentById(Long commentId);
    CommentResponseDto updateComment(Long commentId, Long sessionUserId, CommentRequestDto requestDto);
    void deleteComment(Long commentId, Long sessionUserId);
}
