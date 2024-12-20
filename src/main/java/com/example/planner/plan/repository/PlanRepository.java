package com.example.planner.plan.repository;

import com.example.planner.plan.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT p FROM Plan p WHERE (:userId IS NULL OR p.user.userId = :userId) " +
            "AND (:date IS NULL OR FUNCTION('DATE', p.updatedAt) = :date)")
    Page<Plan> findByUserIdAndUpdatedAt(@Param("userId") Long userId,
                                        @Param("date") LocalDate date,
                                        Pageable pageable);

    @EntityGraph(attributePaths = {"user"})
    Optional<Plan> findPlanByPlanId(Long planId);
}
