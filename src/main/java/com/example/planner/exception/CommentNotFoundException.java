package com.example.planner.exception;

import com.example.planner.constant.common.ErrorMessage;
import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException{

    private final ErrorMessage errorMessage;

    public CommentNotFoundException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
