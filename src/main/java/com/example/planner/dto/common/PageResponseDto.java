package com.example.planner.dto.common;

import com.example.planner.dto.plan.PlanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {

    private PagingDto paging;
    private List<PlanResponseDto> plans;
}
