package com.example.planner.model;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.exception.AuthenticationException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@AllArgsConstructor
@Table(name = "plan")
@NoArgsConstructor
@Getter
public class Plan extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Plan(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void isOwner(Long sessionUserId) {
        if (!this.getUser().getUserId().equals(sessionUserId)) {
            throw new AuthenticationException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }

    public void updatePlan(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
