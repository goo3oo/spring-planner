package com.example.planner.plan.service;

import com.example.planner.common.constant.AuthFailMessage;
import com.example.planner.common.exception.AuthenticationException;
import com.example.planner.plan.constant.PlanFailMessage;
import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import com.example.planner.plan.entity.Plan;
import com.example.planner.plan.exception.PlanNotFoundException;
import com.example.planner.user.entity.User;
import com.example.planner.plan.repository.PlanRepository;
import com.example.planner.user.reopository.UserRepository;
import com.example.planner.common.util.AuthSession;
import com.example.planner.common.util.PlanMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

@Transactional
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public PlanResponseDto createPlan(PlanRequestDto requestDto, HttpSession session) {
        Long userUniqueId = AuthSession.getSession(session);
        User user = userRepository.findById(userUniqueId)
                .orElseThrow(() -> new AuthenticationException(AuthFailMessage.NO_LOGIN_INFO));
        Plan plan = requestDto.dtoToEntity(user);

        planRepository.save(plan);

        return PlanMapper.toDto(plan);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanResponseDto> findAllPlan(String userName, String date) {
        userName = convertEmptyToNull(userName);
        date = convertEmptyToNull(date);

        LocalDate updatedAt = validateAndFormatDate(date);
        List<Plan> plans = planRepository.findByAuthorAndUpdatedAt(userName, updatedAt);

        if (plans.isEmpty()) {
            throw new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND);
        }

        return plans.stream()
                .map(PlanMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PlanResponseDto findPlanById(Long id) {
        Plan plan = planRepository.findPlanById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (plan == null) {
            throw new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND);
        }

        return PlanMapper.toDto(plan);
    }

    @Override
    public PlanResponseDto updatePlan(Long id, PlanRequestDto requestDto) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND));
        plan.updatePlan(requestDto.getTitle(), requestDto.getContent());
        return PlanMapper.toDto(plan);
    }

    @Override
    public void deletePlan(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException(PlanFailMessage.PLAN_NOT_FOUND));
        planRepository.delete(plan);
    }

    private String convertEmptyToNull(String value) {
        return (value != null && value.isEmpty()) ? null : value;
    }

    private LocalDate validateAndFormatDate(String updatedAt) {
        if (updatedAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                return LocalDate.parse(updatedAt, formatter);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다. yyyy-MM-dd 형식으로 입력해주세요.");
            }
        }
        return null;
    }
}
