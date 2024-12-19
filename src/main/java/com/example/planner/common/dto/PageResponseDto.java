package com.example.planner.common.dto;

import com.example.planner.plan.dto.PlanResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDto {

    private PagingDto paging;
    private List<PlanResponseDto> planResponseDtoList;

}
