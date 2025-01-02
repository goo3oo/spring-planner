package com.example.planner.model;

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
