package com.example.planner.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationResponseDto {

    private List<String> errorMessage;

    public static ValidationResponseDto fail(List<String> errorMessage){
        return new ValidationResponseDto(errorMessage);
    }
}
