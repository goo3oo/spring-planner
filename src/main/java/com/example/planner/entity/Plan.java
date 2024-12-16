package com.example.planner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;

@Entity
@AllArgsConstructor
@Table(name = "plan")
@NoArgsConstructor
@Getter
public class Plan extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User authorId;

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
