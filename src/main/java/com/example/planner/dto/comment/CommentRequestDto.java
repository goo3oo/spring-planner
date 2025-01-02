package com.example.planner.dto.comment;

import com.example.planner.constant.common.ValidFailMessages;
import com.example.planner.model.Comment;
import com.example.planner.model.Plan;
import com.example.planner.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = ValidFailMessages.NOT_NULL)
    @Size(min = 1, max = 500 , message = ValidFailMessages.COMMENT_LENGTH)
    private String content;

    public Comment toEntity(User user, Plan plan){
        return new Comment(user, plan, content);
    }
}
