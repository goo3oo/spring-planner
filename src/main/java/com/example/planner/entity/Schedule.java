package com.example.planner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@Table(name = "schedule" )
@NoArgsConstructor
public class Schedule extends BaseEntity{

//    @Column(nullable = false)
//    private Long authorId;
    @Column(nullable = false)
    @NotBlank(message = "작성자를 입력해주세요.")
    private String author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}
