package com.example.planner.plan.service;

import com.example.planner.comment.service.CommentServiceImpl;
import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.common.dto.PageResponseDto;
import com.example.planner.common.dto.PagingDto;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.plan.constant.PlanFailMessage;
import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import com.example.planner.plan.entity.Plan;
import com.example.planner.plan.exception.PlanNotFoundException;
import com.example.planner.user.entity.User;
import com.example.planner.plan.repository.PlanRepository;
import com.example.planner.user.repository.UserRepository;
import com.example.planner.common.util.AuthSession;
import com.example.planner.common.util.PlanMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final CommentServiceImpl commentService;

    @Override
    public PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session) {
        Long userUniqueId = AuthSession.getSession(session);
        User user = userRepository.findById(userUniqueId)
                .orElseThrow(() -> new AuthenticationException(AuthFailMessage.NO_LOGIN_INFO));
        Plan plan = requestDto.toEntity(user);

        planRepository.save(plan);

        return PlanMapper.toDto(plan, commentService.getCommentCountByPlanId(plan.getPlanId()));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto findAllPlan(Long userId, LocalDate date, Pageable pageable) {

        Page<Plan> plans = planRepository.findByUserIdAndUpdatedAt(userId, date, pageable);

        if (plans.isEmpty()) {
            throw new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND);
        }

        Page<PlanResponseDto> dtoPage = plans.map(plan -> {
            int commentCount = commentService.getCommentCountByPlanId(plan.getPlanId());
            return PlanMapper.toDto(plan, commentCount);
        });

        PagingDto pagingDto = PlanMapper.toDto(plans);

        List<PlanResponseDto> dtoList = dtoPage.getContent();

        return new PageResponseDto(pagingDto, dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public PlanResponseDto findPlanById(Long planId) {
        Plan plan = planRepository.findPlanByPlanId(planId)
                .orElseThrow(() -> new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND));

        return PlanMapper.toDto(plan, commentService.getCommentCountByPlanId(plan.getPlanId()));
    }

    @Override
    public PlanResponseDto updatePlan(Long planId, Long sessionUserId, PlanRequestDto requestDto) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND));

        if (!plan.getUser().getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(AuthFailMessage.UNAUTHORIZED_UPDATE_ACCESS);
        }

        plan.updatePlan(requestDto.getTitle(), requestDto.getContent());
        return PlanMapper.toDto(plan, commentService.getCommentCountByPlanId(plan.getPlanId()));
    }

    @Override
    public void deletePlan(Long planId, Long sessionUserId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND));

        if (!plan.getUser().getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(AuthFailMessage.UNAUTHORIZED_DELETE_ACCESS);
        }

        planRepository.delete(plan);
    }
}
