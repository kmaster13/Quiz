package com.example.quiz.model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Person {
    private long id;
    @NotEmpty(message ="Email должен быть не пустой!")
    @Email(message = "Email некорректный!")
    private String email;
}