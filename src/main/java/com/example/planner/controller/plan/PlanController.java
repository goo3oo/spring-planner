package com.example.planner.controller.plan;

import com.example.planner.constant.common.SuccessMessages;
import com.example.planner.dto.common.PageResponseDto;
import com.example.planner.dto.common.SuccessResponseDto;;
import com.example.planner.util.AuthSession;
import com.example.planner.dto.plan.PlanRequestDto;
import com.example.planner.dto.plan.PlanResponseDto;
import com.example.planner.service.plan.PlanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<SuccessResponseDto<PlanResponseDto>> createPlan(
            @Valid @RequestBody PlanRequestDto requestDto,
            HttpSession session
    ) {
            PlanResponseDto responseDto = planService.createPlan(requestDto, session);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(SuccessResponseDto.of(SuccessMessages.CREATE_PLAN_SUCCESS.getMessage(), responseDto));
    }

    @GetMapping
    public ResponseEntity<SuccessResponseDto<PageResponseDto>> findAllPlan(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                    @PageableDefault(size = 10, sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable){

            PageResponseDto pageResponseDto = planService.findAllPlan(userId, date, pageable);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.FIND_PLAN_SUCCESS.getMessage(), pageResponseDto));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<SuccessResponseDto<PlanResponseDto>> findPlanById(
            @PathVariable Long planId
    ) {
            PlanResponseDto responseDto = planService.findPlanById(planId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.FIND_PLAN_SUCCESS.getMessage(), responseDto));
    }

    @PatchMapping("/{planId}")
    public ResponseEntity<SuccessResponseDto<PlanResponseDto>> updatePlan(
            @RequestBody PlanRequestDto requestDto,
            @PathVariable Long planId,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
            PlanResponseDto responseDto = planService.updatePlan(planId, sessionUserId, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.UPDATE_PLAN_SUCCESS.getMessage(), responseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDto<Object>> deletePlan(
            @PathVariable Long id,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
            planService.deletePlan(id, sessionUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(SuccessResponseDto.of(SuccessMessages.DELETE_PLAN_SUCCESS.getMessage()));
    }
}
