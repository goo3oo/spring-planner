package com.example.planner.comment.service;

import com.example.planner.comment.dto.CommentRequestDto;
import com.example.planner.comment.dto.CommentResponseDto;
import jakarta.validation.Valid;

public interface CommentService {

    CommentResponseDto postComment(CommentRequestDto requestDto, Long sessionUserId, Long planId);
}
