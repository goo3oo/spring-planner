package com.example.planner.service.plan;

import com.example.planner.dto.common.PageResponseDto;
import com.example.planner.dto.plan.PlanRequestDto;
import com.example.planner.dto.plan.PlanResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface PlanService {

    PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session);
    PageResponseDto findAllPlan(Long userId, LocalDate date, Pageable pageable);
    PlanResponseDto findPlanById(Long planId);
    PlanResponseDto updatePlan(Long planId, Long sessionUserId, PlanRequestDto requestDto);
    void deletePlan(Long planId, Long sessionUserId);
}
