package com.example.quiz.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class PersonAnswer {
    private long personId;
    private long answerId;
}