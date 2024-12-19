package com.example.planner.common.util;

import com.example.planner.user.dto.UserResponseDto;
import com.example.planner.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {
    public static UserResponseDto toDto(User user){
        return new UserResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getUserName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
