    package com.example.planner.common.util;

    import com.example.planner.common.dto.PagingDto;
    import com.example.planner.plan.dto.PlanResponseDto;
    import com.example.planner.plan.entity.Plan;
    import lombok.AccessLevel;
    import lombok.NoArgsConstructor;
    import org.springframework.data.domain.Page;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class PlanMapper {
        public static PlanResponseDto toDto(Plan plan, int  commentCount){

            return new PlanResponseDto(
                    plan.getPlanId(),
                    plan.getUser().getUserName(),
                    plan.getTitle(),
                    plan.getContent(),
                    commentCount,
                    plan.getCreatedAt(),
                    plan.getUpdatedAt()
            );
        }
        public static PagingDto toDto(Page<Plan> plans){

            return new PagingDto(
                    plans.getTotalElements(),
                    plans.getSize(),
                    plans.getNumber()
            );
        }
    }
