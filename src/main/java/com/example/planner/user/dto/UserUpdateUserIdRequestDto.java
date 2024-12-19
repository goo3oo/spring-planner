package com.example.planner.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdateUserIdRequestDto {
    @NotNull(message = "유저명을 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{3,20}$", message = "유저네임은 3~20자 내로 입력해주세요.\n영어, 숫자, 한글만 가능합니다.")
    private String userName;
}
