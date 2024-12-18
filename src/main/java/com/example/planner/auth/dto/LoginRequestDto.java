package com.example.planner.auth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotNull(message = "이메일을 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "이메일 형식에 맞게 입력해주세요.")
    private String email;

    @NotNull(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$-_+.,:?]{4,20}" +
                      "[^<>?{}&'\"/;%\\s()^=#]*$",
            message = "비밀번호는 4~20자 내로 입력해주세요. 특수문자 '< > ? { } & ' \" / ; % 공백 ( ) ^ = #'를 포함할 수 없습니다.")
    private String password;

}
