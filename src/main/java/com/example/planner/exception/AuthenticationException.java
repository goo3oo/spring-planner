package com.example.planner.exception;

import com.example.planner.constant.common.ErrorMessage;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public AuthenticationException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
