package com.example.planner.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingDto {
    private long total;
    private int pageSize;
    private int current;
}
