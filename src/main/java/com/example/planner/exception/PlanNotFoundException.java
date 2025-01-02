package com.example.planner.exception;

import com.example.planner.constant.PlanFailMessage;
import lombok.Getter;

@Getter
public class PlanNotFoundException extends RuntimeException{
    private final PlanFailMessage failReason;

    public PlanNotFoundException(PlanFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
