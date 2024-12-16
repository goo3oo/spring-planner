package com.example.planner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PlanResponseDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDate createdAt;
    private LocalDate updatedAt;


}
