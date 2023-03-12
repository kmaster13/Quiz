package com.example.quiz.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Answer {
    private Long id;
    private String name;
    private Boolean flag;
    private Long questionId;
}
