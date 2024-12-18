package com.example.planner.plan.controller;

import com.example.planner.common.dto.ApiResponseDto;
import com.example.planner.common.dto.ValidationResponseDto;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.common.util.AuthSession;
import com.example.planner.common.util.BindingResultUtils;
import com.example.planner.plan.constant.PlanSuccessMessage;
import com.example.planner.plan.dto.PlanListResponseDto;
import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import com.example.planner.plan.exception.PlanNotFoundException;
import com.example.planner.plan.service.PlanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanService planService;

    @PostMapping
    public ResponseEntity<?> createPlan(
            @Valid @RequestBody PlanRequestDto requestDto,
            BindingResult bindingResult,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }

        try {
            PlanResponseDto responseDto = planService.createPlan(requestDto, session);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponseDto.success(PlanSuccessMessage.CREATE_PLAN_SUCCESS.getMessage(), responseDto));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<PlanListResponseDto>> findAllPlan(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String date) {
        try {
            PlanListResponseDto planListResponseDto = new PlanListResponseDto(planService.findAllPlan(userName, date));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(PlanSuccessMessage.FIND_PLAN_SUCCESS.getMessage(), planListResponseDto));
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<PlanResponseDto>> findPlanById(
            @PathVariable Long id) {
        try {
            PlanResponseDto responseDto = planService.findPlanById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(PlanSuccessMessage.FIND_PLAN_SUCCESS.getMessage(), responseDto));
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePlan(
            @Valid @RequestBody PlanRequestDto requestDto,
            BindingResult bindingResult,
            @PathVariable Long id,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ValidationResponseDto.fail(BindingResultUtils.extractErrorMessages(bindingResult)));
        }
        try {
            PlanResponseDto responseDto = planService.updatePlan(id, sessionUserId, requestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(PlanSuccessMessage.UPDATE_PLAN_SUCCESS.getMessage(), responseDto));
        } catch (PlanNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deletePlan(
            @PathVariable Long id,
            @SessionAttribute(name = AuthSession.SESSION_KEY, required = true) Long sessionUserId
    ) {
        try {
            planService.deletePlan(id, sessionUserId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponseDto.success(PlanSuccessMessage.DELETE_PLAN_SUCCESS.getMessage()));
        }catch (PlanNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponseDto.fail(e.getMessage()));
        }
    }
}
