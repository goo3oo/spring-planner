package com.example.planner.dto.user;

import com.example.planner.constant.common.ValidFailMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdateUserIdRequestDto {

    @NotNull(message = ValidFailMessages.NOT_NULL)
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{3,20}$", message = ValidFailMessages.USERNAME_LENGTH)
    private String userName;
}