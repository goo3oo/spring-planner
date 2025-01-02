package com.example.planner.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingDto {
    private long total;
    private int pageSize;
    private int current;
}
