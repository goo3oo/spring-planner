package com.example.planner.comment.service;

import com.example.planner.comment.constant.CommentFailMessage;
import com.example.planner.comment.dto.CommentRequestDto;
import com.example.planner.comment.dto.CommentResponseDto;
import com.example.planner.comment.entity.Comment;
import com.example.planner.comment.exception.CommentNotFoundException;
import com.example.planner.comment.repository.CommentRepository;
import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.common.util.CommentMapper;
import com.example.planner.plan.constant.PlanFailMessage;
import com.example.planner.plan.entity.Plan;
import com.example.planner.plan.exception.PlanNotFoundException;
import com.example.planner.plan.repository.PlanRepository;
import com.example.planner.user.entity.User;
import com.example.planner.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto postComment(CommentRequestDto requestDto, Long sessionUserId, Long planId) {

        if(sessionUserId == null){
            throw new AuthenticationException(AuthFailMessage.USER_LOGGED_OUT);
        }
        User user = userRepository.findById(sessionUserId)
                .orElseThrow(()-> new AuthenticationException(AuthFailMessage.USER_NOT_FOUND));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(()-> new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND));

        Comment comment = requestDto.toEntity(user, plan);

        commentRepository.save(comment);

        return CommentMapper.toDto(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllCommentByUserId(Long userId) {
        List<Comment> comments = commentRepository.findAllByUser_Id(userId);

        if (comments.isEmpty()) {
            throw new CommentNotFoundException(CommentFailMessage.COMMENT_NOT_FOUND);
        }
        return comments.stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllCommentByPlanId(Long planId) {
        List<Comment> comments = commentRepository.findAllByPlan_Id(planId);

        if (comments.isEmpty()) {
            throw new CommentNotFoundException(CommentFailMessage.COMMENT_NOT_FOUND);
        }
        return comments.stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto findCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(CommentFailMessage.COMMENT_NOT_FOUND));
        return CommentMapper.toDto(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(CommentFailMessage.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }

    @Override
    public CommentResponseDto updateComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(CommentFailMessage.COMMENT_NOT_FOUND));
        comment.
    }
}

