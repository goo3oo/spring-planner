package com.example.planner.comment.controller;

import com.example.planner.comment.constant.CommentSuccessMessage;
import com.example.planner.comment.dto.CommentRequestDto;
import com.example.planner.comment.dto.CommentResponseDto;
import com.example.planner.comment.service.CommentService;
import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.common.dto.ValidationResponseDto;
import com.example.planner.common.util.AuthSession;
import com.example.planner.common.util.BindingResultUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{planId}")
    public ResponseEntity<?> postComment(
            @Valid @RequestBody CommentRequestDto requestDto,
            BindingResult bindingResult,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId,
            @PathVariable Long planId
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }
        try {
            CommentResponseDto responseDto = commentService.postComment(requestDto, sessionUserId, planId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponseDto.success(CommentSuccessMessage.POST_COMMENT_SUCCESS.getMessage(), responseDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }


}
