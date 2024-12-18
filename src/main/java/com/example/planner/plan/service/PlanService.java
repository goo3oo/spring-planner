package com.example.planner.plan.service;

import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session);
    List<PlanResponseDto> findAllPlan(String userId, String date);
    PlanResponseDto findPlanById(Long id);
    PlanResponseDto updatePlan(Long id, Long sessionUserId, PlanRequestDto requestDto);
    void deletePlan(Long id, Long sessionUserId);
}
