package com.example.planner.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String email;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
