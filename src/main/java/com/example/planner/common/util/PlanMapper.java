    package com.example.planner.common.util;

    import com.example.planner.plan.dto.PlanResponseDto;
    import com.example.planner.plan.entity.Plan;
    import lombok.AccessLevel;
    import lombok.NoArgsConstructor;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class PlanMapper {
        public static PlanResponseDto toDto(Plan plan){
            String userName = (plan.getUser() != null) ? plan.getUser().getUserName() : "사용자 삭제됨";
            return new PlanResponseDto(
                    plan.getId(),
                    userName,
                    plan.getTitle(),
                    plan.getContent(),
                    plan.getCreatedAt(),
                    plan.getUpdatedAt()
            );
        }
    }
