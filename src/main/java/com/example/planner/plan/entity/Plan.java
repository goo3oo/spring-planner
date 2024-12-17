package com.example.planner.plan.entity;

import com.example.planner.common.entity.BaseEntity;
import com.example.planner.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@Table(name = "plan")
@NoArgsConstructor
@Getter
public class Plan extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unique_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Column(nullable = false)
    private String content;


    public void updatePlan(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
