package com.example.planner.user.entity;

import com.example.planner.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    public User(String email, String userName, String password){
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }
}
