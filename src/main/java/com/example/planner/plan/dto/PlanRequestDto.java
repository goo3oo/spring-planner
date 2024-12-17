    package com.example.planner.plan.dto;

    import com.example.planner.plan.entity.Plan;
    import com.example.planner.user.entity.User;
    import lombok.Getter;

    @Getter
    public class PlanRequestDto {

        private String title;
        private String content;

        public Plan dtoToEntity(User user){
            return new Plan(user, title, content);
        }
    }
