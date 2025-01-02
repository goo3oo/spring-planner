package com.example.planner.exception;

import com.example.planner.constant.CommentFailMessage;
import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException{
    private final CommentFailMessage failReason;

    public CommentNotFoundException(CommentFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
