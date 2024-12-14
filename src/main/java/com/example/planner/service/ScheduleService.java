package com.example.planner.service;

import com.example.planner.dto.ScheduleRequestDto;
import com.example.planner.entity.Schedule;
import com.example.planner.reopository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto.getAuthor(), requestDto.getTitle(), requestDto.getContent());
        scheduleRepository.save(schedule);
    }
}
