package com.example.planner.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor

public class PlanListResponseDto {
    private Page<PlanResponseDto> plans;
}
