package com.example.planner.service.comment;

import com.example.planner.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryService {
    // 순환참조때문에 생성
    private final CommentRepository commentRepository;

    public int getCommentCount(Long planId){
        return commentRepository.countByPlan_PlanId(planId);
    }

    public Map<Long, Integer> getCommentCounts(List<Long> planIds){
        List<Object[]> commentCountResults =  commentRepository.countCommentsByPlanIds(planIds);

        return commentCountResults.stream()
                .collect(Collectors.toMap(
                        item -> ((Number) item[0]).longValue(),
                        item -> ((Number) item[1]).intValue()
                ));
    }
}
