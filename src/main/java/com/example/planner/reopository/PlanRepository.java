package com.example.planner.reopository;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @EntityGraph(attributePaths = {"authorId"})
    @Query("SELECT p FROM Plan p WHERE (:userId IS NULL OR p.authorId.userId = :userId) " +
            "AND (:updatedAt IS NULL OR p.updatedAt = :updatedAt)")
    List<Plan> findByAuthorAndUpdatedAt(@Param("userId") String userId,
                                        @Param("updatedAt") LocalDate updatedAt);

    Optional<Plan> findPlanById(Long id);
}
