package com.example.planner.plan.exception;

import com.example.planner.plan.constant.PlanFailMessage;
import lombok.Getter;

@Getter
public class PlanNotFoundException extends RuntimeException{
    private final PlanFailMessage failReason;

    public PlanNotFoundException(PlanFailMessage failReason){
        super(failReason.getMessage());
        this.failReason = failReason;
    }
}
