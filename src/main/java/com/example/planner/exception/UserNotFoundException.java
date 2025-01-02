package com.example.planner.exception;

import com.example.planner.constant.UserFailMessage;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private final UserFailMessage failReason;

    public UserNotFoundException(UserFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
