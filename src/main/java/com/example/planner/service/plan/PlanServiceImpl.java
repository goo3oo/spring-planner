package com.example.planner.service.plan;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.dto.common.PageResponseDto;
import com.example.planner.dto.common.PagingDto;
import com.example.planner.exception.AuthenticationException;
import com.example.planner.dto.plan.PlanRequestDto;
import com.example.planner.dto.plan.PlanResponseDto;
import com.example.planner.model.Comment;
import com.example.planner.model.Plan;
import com.example.planner.exception.PlanNotFoundException;
import com.example.planner.model.User;
import com.example.planner.repository.plan.PlanRepository;
import com.example.planner.service.comment.CommentQueryService;
import com.example.planner.service.user.UserService;
import com.example.planner.util.AuthSession;
import com.example.planner.util.mapper.PlanMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final CommentQueryService commentQueryService;
    private final UserService userService;

    @Override
    public PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session) {
        Long userId = AuthSession.getSession(session);
        User user = userService.findUserByUserIdOrThrow(userId);
        Plan plan = requestDto.toEntity(user);

        planRepository.save(plan);

        return PlanMapper.toDto(plan,0);
    }

    @Transactional(readOnly = true)
    public PageResponseDto findAllPlan(Long userId, LocalDate date, Pageable pageable) {
        Page<Plan> plans = planRepository.findByUserIdAndUpdatedAt(userId, date, pageable);

        if (plans.isEmpty()) {
            throw new PlanNotFoundException(ErrorMessage.PLAN_NOT_FOUND);
        }

        List<Long> planIds = plans.stream()
                .map(Plan::getPlanId)
                .collect(Collectors.toList());
        // planId와 commentCount 키, 값 리스트
        Map<Long, Integer> commentCounts = commentQueryService.getCommentCounts(planIds);
        
        List<PlanResponseDto> dtoList = plans.stream()
                .map(plan -> {
                    int commentCount = commentCounts.getOrDefault(plan.getPlanId(), 0);
                    return PlanMapper.toDto(plan, commentCount);
                })
                .toList();

        PagingDto pagingDto = PlanMapper.toDto(plans);

        return new PageResponseDto(pagingDto, dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanResponseDto findPlanById(Long planId) {
        Plan plan = planRepository.findPlanByPlanId(planId)
                .orElseThrow(() -> new PlanNotFoundException(ErrorMessage.PLAN_NOT_FOUND));

        return PlanMapper.toDto(plan, commentQueryService.getCommentCount(planId));
    }

    @Override
    public PlanResponseDto updatePlan(Long planId, Long sessionUserId, PlanRequestDto requestDto) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(ErrorMessage.PLAN_NOT_FOUND));
        // entity 메서드로 변경 isOwner
        if (!plan.getUser().getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }

        plan.updatePlan(requestDto.getTitle(), requestDto.getContent());

        return PlanMapper.toDto(plan, commentQueryService.getCommentCount(planId));
    }

    @Override
    public void deletePlan(Long planId, Long sessionUserId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(ErrorMessage.PLAN_NOT_FOUND));
        // entity 메서드로 변경 isOwner
        if (!plan.getUser().getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }

        planRepository.delete(plan);
    }

    @Override
    public Plan findById(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(ErrorMessage.PLAN_NOT_FOUND));
    }
}
