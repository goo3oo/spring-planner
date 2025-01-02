package com.example.planner.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ErrorResponseDto {

    private final boolean success;
    private final String message;
    private final List<String> errors;

    public static ErrorResponseDto of(String message) {
        return ErrorResponseDto.builder()
                .success(false)
                .message(message)
                .errors(new ArrayList<>())
                .build();
    }

    public static ErrorResponseDto of(String message, List<String> errors) {
        return ErrorResponseDto.builder()
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
}
