package com.example.planner.dto.plan;

import com.example.planner.constant.common.ValidFailMessages;
import com.example.planner.model.Plan;
import com.example.planner.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PlanRequestDto {

    @NotEmpty(message = ValidFailMessages.NOT_NULL)
    @Size(min = 1, max = 50, message = ValidFailMessages.PLAN_TITLE_LENGTH)
    private String title;

    @NotEmpty(message = ValidFailMessages.NOT_NULL)
    @Size(max = 1000, message = ValidFailMessages.PLAN_CONTENT_LENGTH)
    private String content;

    public Plan toEntity(User user) {
        return new Plan(user, title, content);
    }
}
