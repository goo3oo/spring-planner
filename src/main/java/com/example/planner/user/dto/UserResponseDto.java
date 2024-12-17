package com.example.planner.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String userId;
    private String email;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
