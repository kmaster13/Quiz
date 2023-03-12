package com.example.quiz.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Count {
    private long personId;
    private long questionId;
    private int count;
}