package com.example.planner.exception;

import com.example.planner.constant.common.AuthFailMessage;
import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException{
    private final AuthFailMessage failReason;

    public AuthenticationException(AuthFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
