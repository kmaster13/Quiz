package com.example.quiz.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Question {
    private Long id;
    private String title;
    private List<Answer> answers;
}