package com.example.planner.controller.comment;

import com.example.planner.constant.common.SuccessMessages;
import com.example.planner.dto.comment.CommentListResponseDto;
import com.example.planner.dto.comment.CommentRequestDto;
import com.example.planner.dto.comment.CommentResponseDto;
import com.example.planner.dto.common.SuccessResponseDto;
import com.example.planner.service.comment.CommentService;
import com.example.planner.util.AuthSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{planId}")
    public ResponseEntity<SuccessResponseDto<CommentResponseDto>> postComment(
            @Valid @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId,
            @PathVariable Long planId
    ) {
            CommentResponseDto responseDto = commentService.postComment(requestDto, sessionUserId, planId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(SuccessResponseDto.of(SuccessMessages.POST_COMMENT_SUCCESS.getMessage(),responseDto));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<SuccessResponseDto<CommentListResponseDto>> findAllCommentByUserId(
            @PathVariable Long userId
    ) {
            CommentListResponseDto responseDto = new CommentListResponseDto(commentService.findAllCommentByUserId(userId));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.FIND_COMMENT_BY_COMMENT_SUCCESS.getMessage(), responseDto));
    }

    @GetMapping("plans/{planId}")
    public ResponseEntity<SuccessResponseDto<CommentListResponseDto>> findAllCommentByPlanId(
            @PathVariable Long planId
    ) {
            CommentListResponseDto responseDto = new CommentListResponseDto(commentService.findAllCommentByPlanId(planId));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.USER_FIND_SUCCESS.getMessage(),responseDto));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<SuccessResponseDto<CommentResponseDto>> findCommentById(
            @PathVariable Long commentId
    ) {
            CommentResponseDto responseDto = commentService.findCommentById(commentId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.USER_FIND_SUCCESS.getMessage(),responseDto));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<SuccessResponseDto<CommentResponseDto>> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
            CommentResponseDto responseDto = commentService.updateComment(commentId, sessionUserId, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.UPDATE_COMMENT_SUCCESS.getMessage(),responseDto));
    }

    @DeleteMapping("/{commentId}")
        public ResponseEntity<SuccessResponseDto<Object>> deleteComment(
            @PathVariable Long commentId,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
            commentService.deleteComment(commentId, sessionUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.DELETE_COMMENT_SUCCESS.getMessage()));
    }
}