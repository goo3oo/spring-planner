package com.example.planner.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private String email;
    private String userName;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
