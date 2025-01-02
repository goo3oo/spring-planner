package com.example.planner.dto.user;

import com.example.planner.constant.common.ValidFailMessages;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdatePasswordRequestDto {

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9~!@#$-_+.,:?]{4,20}+[^<>?{}&'\"/;%\\s()^=#]*$", message = ValidFailMessages.PASSWORD_LENGTH)
    private String password;
}