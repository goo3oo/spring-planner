package com.example.planner.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String message;
    private boolean success;
    private T data;

    public static <T> ApiResponseDto<T> success(String message, T data){
        return new ApiResponseDto<>(message, true, data);
    }

    public static <T> ApiResponseDto<T> fail(String message){
        return new ApiResponseDto<>(message, false, null);
    }

    public static ApiResponseDto<Void> success(String message){
        return new ApiResponseDto<>(message, true, null);
    }
}
