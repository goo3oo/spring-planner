package com.example.planner.comment.exception;

import com.example.planner.comment.constant.CommentFailMessage;
import lombok.Getter;

@Getter
public class CommentNotFoundException extends RuntimeException{

    private final CommentFailMessage failReason;

    public CommentNotFoundException(CommentFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
