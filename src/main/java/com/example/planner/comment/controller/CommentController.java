package com.example.planner.comment.controller;

import com.example.planner.comment.constant.CommentSuccessMessage;
import com.example.planner.comment.dto.CommentListResponseDto;
import com.example.planner.comment.dto.CommentRequestDto;
import com.example.planner.comment.dto.CommentResponseDto;
import com.example.planner.comment.exception.CommentNotFoundException;
import com.example.planner.comment.repository.CommentRepository;
import com.example.planner.comment.service.CommentService;
import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.common.dto.ValidationResponseDto;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.common.util.AuthSession;
import com.example.planner.common.util.BindingResultUtils;
import com.example.planner.plan.exception.PlanNotFoundException;
import com.example.planner.user.exception.UserNotFoundException;
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
    private final CommentRepository commentRepository;

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
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<ApiResponseDto<CommentListResponseDto>> findAllCommentByUserId(
            @PathVariable Long userId
    ) {
        try {
            CommentListResponseDto responseDto = new CommentListResponseDto(commentService.findAllCommentByUserId(userId));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(CommentSuccessMessage.FIND_ALL_COMMENTS_BY_USER_SUCCESS.getMessage(), responseDto));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @GetMapping("plans/{planId}")
    public ResponseEntity<ApiResponseDto<CommentListResponseDto>> findAllCommentByPlanId(
            @PathVariable Long planId
    ) {
        try {
            CommentListResponseDto responseDto = new CommentListResponseDto(commentService.findAllCommentByPlanId(planId));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(CommentSuccessMessage.FIND_ALL_COMMENTS_BY_PLAN_SUCCESS.getMessage(), responseDto));
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<CommentResponseDto>> findCommentById(
            @PathVariable Long commentId
    ) {
        try {
            CommentResponseDto responseDto = commentService.findCommentById(commentId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(CommentSuccessMessage.FIND_COMMENT_BY_COMMENT_SUCCESS.getMessage(), responseDto));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto,
            BindingResult bindingResult,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }
        try {
            CommentResponseDto responseDto = commentService.updateComment(commentId, sessionUserId, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(CommentSuccessMessage.UPDATE_COMMENT_SUCCESS.getMessage(), responseDto));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponseDto<Void>> deleteComment(
            @PathVariable Long commentId,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        try {
            commentService.deleteComment(commentId, sessionUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(CommentSuccessMessage.DELETE_COMMENT_SUCCESS.getMessage()));
        } catch (CommentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }
}
