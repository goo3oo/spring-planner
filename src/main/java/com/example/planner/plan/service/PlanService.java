package com.example.planner.plan.service;

import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session);
    List<PlanResponseDto> findAllPlan(Long userId, LocalDate date);
    PlanResponseDto findPlanById(Long planId);
    PlanResponseDto updatePlan(Long planId, Long sessionUserId, PlanRequestDto requestDto);
    void deletePlan(Long planId, Long sessionUserId);
}
