package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;

import java.util.List;

public interface PlanService {
    void createPlan(PlanRequestDto requestDto);
    List<PlanResponseDto> findAllPlan(String author, String date);
    PlanResponseDto findPlanById(Long id);
    void updatePlan(Long id, PlanRequestDto requestDto);
    void deletePlan(Long id);
}
