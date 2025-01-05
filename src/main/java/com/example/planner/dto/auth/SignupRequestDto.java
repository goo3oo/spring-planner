package com.example.planner.dto.auth;

import com.example.planner.constant.common.ValidFailMessages;
import com.example.planner.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = ValidFailMessages.INVALID_EMAIL_FORMAT)
    private String email;

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{3,20}$", message = ValidFailMessages.USERNAME_LENGTH)
    private String userName;

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$-_+.,:?]{4,20}+[^<>?{}&'\"/;%\\s()^=#]*$", message = ValidFailMessages.PASSWORD_LENGTH)
    private String password;

    public User toEntity(String encodedPassword) {
        return new User(email, userName, encodedPassword);
    }
}
