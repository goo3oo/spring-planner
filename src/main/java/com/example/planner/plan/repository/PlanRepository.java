package com.example.planner.plan.repository;

import com.example.planner.plan.entity.Plan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT p FROM Plan p WHERE (:userId IS NULL OR p.user.userId = :userId) " +
            "AND (:updatedAt IS NULL OR p.updatedAt = :updatedAt)")
    List<Plan> findByAuthorAndUpdatedAt(@Param("userId") String userId,
                                        @Param("updatedAt") LocalDate updatedAt);

    @EntityGraph(attributePaths = {"user"})
    Optional<Plan> findPlanById(Long id);
}