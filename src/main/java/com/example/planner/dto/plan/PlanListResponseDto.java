package com.example.planner.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor

public class PlanListResponseDto {
    private Page<PlanResponseDto> plans;
}
