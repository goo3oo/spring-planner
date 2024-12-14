package com.example.planner.controller;

import com.example.planner.dto.ApiResponseDto;
import com.example.planner.dto.ScheduleRequestDto;
import com.example.planner.dto.ScheduleResponseDto;
import com.example.planner.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planner")
@RequiredArgsConstructor

public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ApiResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(new ApiResponseDto("일정이 등록되었습니다.", true), HttpStatus.CREATED);
    }

}
