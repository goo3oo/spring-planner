package com.example.planner.util.mapper;

import com.example.planner.dto.user.UserResponseDto;
import com.example.planner.model.User;
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
