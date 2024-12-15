package com.example.planner.service;

import com.example.planner.dto.PlanRequestDto;
import com.example.planner.dto.PlanResponseDto;
import com.example.planner.entity.Plan;
import com.example.planner.reopository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PlanServiceImpl implements PlanService{

    private final PlanRepository scheduleRepository;
    private final PlanRepository planRepository;

    @Override
    public void createPlan(PlanRequestDto requestDto) {
        Plan plan = new Plan(requestDto.getAuthor(), requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(plan);
    }

    @Override
    public List<PlanResponseDto> findAllPlan(String author, String date) {
        author = convertEmptyToNull(author);
        date = convertEmptyToNull(date);

        LocalDate updatedAt = validateAndFormatDate(date);
        List<Plan> plans = planRepository.findByAuthorAndUpdatedAt(author,updatedAt);
        return plans.stream()
                .map(schedule -> new PlanResponseDto(
                        schedule.getId(),
                        schedule.getAuthor(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt()
                ))
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
        return scheduleRepository.findPlanById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
