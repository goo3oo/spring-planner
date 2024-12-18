package com.example.planner.user.exception;

import com.example.planner.user.constant.UserFailMessage;

public class UserNotFoundException extends RuntimeException{

    private UserFailMessage failReason;

    public UserNotFoundException(UserFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }

}
