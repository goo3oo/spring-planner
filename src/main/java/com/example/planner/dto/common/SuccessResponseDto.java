package com.example.planner.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SuccessResponseDto<T> {

    private final boolean success;
    private final String message;
    private final T data;

    public static SuccessResponseDto<Object> of(String message){
        return SuccessResponseDto.builder()
                .success(true)
                .message(message)
                .data(new Object())
                .build();
    }

    public static <T>SuccessResponseDto<T> of(String message, T data) {
        return SuccessResponseDto.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }
}
