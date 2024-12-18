package com.example.planner.comment.repository;

import com.example.planner.comment.dto.CommentResponseDto;
import com.example.planner.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser_Id(Long userId);
    List<Comment> findAllByPlan_Id(Long planId);
}
