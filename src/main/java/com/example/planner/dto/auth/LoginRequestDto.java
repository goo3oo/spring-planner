package com.example.planner.dto.auth;

import com.example.planner.constant.common.ValidFailMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = ValidFailMessages.INVALID_EMAIL_FORMAT)
    private String email;

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$-_+.,:?]{4,20}[^<>?{}&'\"/;%\\s()^=#]*$", message = ValidFailMessages.PASSWORD_LENGTH)
    private String password;
}
