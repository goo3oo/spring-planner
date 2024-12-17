package com.example.planner.plan.service;

import com.example.planner.plan.dto.PlanRequestDto;
import com.example.planner.plan.dto.PlanResponseDto;
import com.example.planner.plan.entity.Plan;
import com.example.planner.user.entity.User;
import com.example.planner.plan.repository.PlanRepository;
import com.example.planner.user.reopository.UserRepository;
import com.example.planner.util.AuthSession;
import com.example.planner.util.PlanMapper;
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

public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Override
    public void createPlan(PlanRequestDto requestDto, HttpSession session) {
        Long userUniqueId = AuthSession.getSession(session);
        User user = userRepository.findById(userUniqueId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "일치하는 사용자 정보가 없습니다."));
        Plan plan = requestDto.dtoToEntity(user);

        planRepository.save(plan);
    }

    @Override
    public List<PlanResponseDto> findAllPlan(String userId, String date) {
        userId = convertEmptyToNull(userId);
        date = convertEmptyToNull(date);

        LocalDate updatedAt = validateAndFormatDate(date);
        List<Plan> plans = planRepository.findByAuthorAndUpdatedAt(userId,updatedAt);

        return plans.stream()
                .map(PlanMapper::planToDto)
                .collect(Collectors.toList());
    }

    private String convertEmptyToNull(String value){
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

    @Override
    public PlanResponseDto findPlanById(Long id) {
        Plan plan = planRepository.findPlanById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return PlanMapper.planToDto(plan);

    }

    @Override
    @Transactional
    public void updatePlan(Long id, PlanRequestDto requestDto) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"id에 해당하는 일정이 없습니다."));
        plan.updatePlan(requestDto.getTitle(), requestDto.getContent());
    }

    @Override
    @Transactional
    public void deletePlan(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"id에 해당하는 일정이 없습니다."));
        planRepository.delete(plan);
    }
}
