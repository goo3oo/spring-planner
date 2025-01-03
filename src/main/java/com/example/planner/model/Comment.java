package com.example.planner.model;

import com.example.planner.constant.common.ErrorMessage;
import com.example.planner.exception.LoginException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plan plan;

    @Column(nullable = false)
    private String content;

    public Comment(User user, Plan plan, String content){
        this.user = user;
        this.plan = plan;
        this.content = content;
    }

    public void isOwner(Long userId) {
        if(!this.getUser().getUserId().equals(userId)) {
            throw new LoginException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }

    public void updateComment(String content){
        this.content = content;
    }
}
