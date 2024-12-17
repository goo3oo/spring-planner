package com.example.planner.common.exception;

import com.example.planner.common.constant.AuthFailMessage;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException{
    private final AuthFailMessage failReason;

    public AuthenticationException(AuthFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
