package com.example.planner.plan.controller;

import com.example.planner.common.dto.ApiResponseDto;

import com.example.planner.plan.dto.PlanListResponseDto;
import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import com.example.planner.plan.service.PlanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor

public class    PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createPlan(@RequestBody PlanRequestDto requestDto, HttpSession session) {
        planService.createPlan(requestDto, session);
        return new ResponseEntity<>(new ApiResponseDto("일정이 등록되었습니다.", true), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PlanListResponseDto> findAllPlan(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String date){
        PlanListResponseDto planListResponseDto = new PlanListResponseDto(planService.findAllPlan(userId, date));
        return new ResponseEntity<>(planListResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findPlanById(
            @PathVariable Long id){
        return new ResponseEntity<>(planService.findPlanById(id),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDto> updatePlan(
            @RequestBody PlanRequestDto requestDto,
            @PathVariable Long id){
        planService.updatePlan(id,requestDto);
        return new ResponseEntity<>(new ApiResponseDto("일정이 수정되었습니다.",true),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> deletePlan(
            @PathVariable Long id){
        planService.deletePlan(id);
        return new ResponseEntity<>(new ApiResponseDto("일정이 삭제되었습니다.",true),HttpStatus.OK);
    }
}
