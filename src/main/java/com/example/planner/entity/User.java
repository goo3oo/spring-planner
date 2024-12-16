package com.example.planner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column(name = "user_id", nullable = false, unique = true)
    @NotBlank(message = "Id를 입력해주세요.")
    private String userId;

    @Column(nullable = false)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Email
    @Column(nullable = false, unique = true)
    @NotBlank(message = "email 주소를 입력해주세요.")
    private String email;

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
