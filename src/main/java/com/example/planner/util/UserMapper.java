package com.example.planner.util;

import com.example.planner.dto.UserResponseDto;
import com.example.planner.entity.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {
    public static UserResponseDto UserToDto(User user){
        return new UserResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
