package com.example.planner.reopository;

import com.example.planner.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    @Query("SELECT p FROM Plan p WHERE (:author IS NULL OR p.author = :author) " +
            "AND (:updatedAt IS NULL OR p.updatedAt = :updatedAt)")
    List<Plan> findByAuthorAndUpdatedAt(@Param("author") String author,
                                        @Param("updatedAt") LocalDate updatedAt);
}
