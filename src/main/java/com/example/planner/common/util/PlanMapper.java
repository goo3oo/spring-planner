package com.example.planner.common.util;

import com.example.planner.plan.dto.PlanResponseDto;
import com.example.planner.plan.entity.Plan;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlanMapper {
    public static PlanResponseDto toDto(Plan plan){
        return new PlanResponseDto(
                plan.getId(),
                plan.getUser().getUserName(),
                plan.getTitle(),
                plan.getContent(),
                plan.getCreatedAt(),
                plan.getUpdatedAt()
        );
    }
}
