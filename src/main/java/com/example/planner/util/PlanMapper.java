package com.example.planner.util;

import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import lombok.NoArgsConstructor;

@NoArgsConstructor

public class PlanMapper {
    public static PlanResponseDto planToDto(Plan plan){
        return new PlanResponseDto(
                plan.getId(),
                plan.getAuthorId().getUserId(),
                plan.getTitle(),
                plan.getContent(),
                plan.getCreatedAt(),
                plan.getUpdatedAt()
        );
    }
}
