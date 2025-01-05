package com.example.planner.repository.comment;

import com.example.planner.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<Comment>> findAllByUser_UserId(Long userId);

    Optional<List<Comment>> findAllByPlan_PlanId(Long planId);

    int countByPlan_PlanId(Long planId);

    @Query("SELECT c.plan.planId as planId, COUNT(c) as commentCount FROM Comment c WHERE c.plan.planId IN :planIds GROUP BY c.plan.planId")
    List<Object[]> countCommentsByPlanIds(@Param("planIds") List<Long> planIds);
}
