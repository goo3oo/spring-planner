package com.example.planner.plan.service;

import com.example.planner.common.dto.PageResponseDto;
import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session);
    PageResponseDto findAllPlan(Long userId, LocalDate date, Pageable pageable);
    PlanResponseDto findPlanById(Long planId);
    PlanResponseDto updatePlan(Long planId, Long sessionUserId, PlanRequestDto requestDto);
    void deletePlan(Long planId, Long sessionUserId);
}
