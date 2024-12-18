    package com.example.planner.plan.dto;

    import com.example.planner.plan.entity.Plan;
    import com.example.planner.user.entity.User;
    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import lombok.Getter;

    @Getter
    public class PlanRequestDto {

        @NotEmpty(message = "일정 제목을 입력해주세요.")
        @Size(min = 1, max = 50, message = "일정 제목은 1~50자 내로 입력해주세요.")
        private String title;

        @NotNull(message = "일정 내용을 입력해주세요.")
        @Size(max = 1000, message = "일정 내용은 최대 1000글자까지 입력할 수 있습니다.")
        private String content;

        public Plan toEntity(User user){
            return new Plan(user, title, content);
        }
    }
