package com.example.planner.model;

import com.example.planner.config.PasswordEncoder;
import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.exception.AuthenticationException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public void validatePassword(PasswordEncoder passwordEncoder, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, this.getPassword())) {
            throw new AuthenticationException(ErrorMessage.INVALID_PASSWORD);
        }
    }

    public void isOwner(Long sessionId){
        if(!this.getUserId().equals(sessionId)){
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }

    public void updatePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }
}

