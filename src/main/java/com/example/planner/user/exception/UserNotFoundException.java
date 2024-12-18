package com.example.planner.user.exception;

import com.example.planner.user.constant.UserFailMessage;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{
    private final UserFailMessage failReason;

    public UserNotFoundException(UserFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
