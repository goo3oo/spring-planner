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
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "비밀번호를 입력해주세요.")
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
