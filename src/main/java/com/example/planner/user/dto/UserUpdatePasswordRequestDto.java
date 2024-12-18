package com.example.planner.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdatePasswordRequestDto {

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$-_+.,:?]{4,20}" +
            "[^<>?{}&'\"/;%\\s()^=#]*$",
            message = "비밀번호는 4~20자 내로 입력해주세요. 특수문자 '< > ? { } & ' \" / ; % 공백 ( ) ^ = #'를 포함할 수 없습니다.")
    private String password;

}
