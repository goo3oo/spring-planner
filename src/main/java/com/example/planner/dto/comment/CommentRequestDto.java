package com.example.planner.dto.comment;

import com.example.planner.model.Comment;
import com.example.planner.model.Plan;
import com.example.planner.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용을 등록해 주세요.")
    @Size(min = 1, max = 500 , message = "댓글은 1~500자 내로 입력해주세요.")
    private String content;

    public Comment toEntity(User user, Plan plan){
        return new Comment(user, plan, content);
    }
}
