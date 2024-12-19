package com.example.planner.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserListResponseDto {
    private List<UserResponseDto> users;
}

