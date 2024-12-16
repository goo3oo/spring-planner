    package com.example.planner.dto;

    import com.example.planner.entity.Plan;
    import com.example.planner.entity.User;
    import lombok.Getter;

    @Getter
    public class PlanRequestDto {

        private String userId;
        private String title;
        private String content;

        public Plan dtoToEntity(User user){
            return new Plan(user, title, content);
        }
    }
