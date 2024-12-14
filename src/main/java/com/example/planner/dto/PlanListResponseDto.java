package com.example.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor

public class PlanListResponseDto {
    private List<PlanResponseDto> plans;
}
