package com.example.planner.exception;

import com.example.planner.constant.common.ErrorMessage;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public UserNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
