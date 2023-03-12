package com.example.quiz.dao;


import com.example.quiz.mapper.CountMapper;
import com.example.quiz.mapper.PersonCountMapper;
import com.example.quiz.mapper.QuestionCountMapper;
import com.example.quiz.model.Count;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatsDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Count countAllResponses() {
        return jdbcTemplate.query("select count(answer_id) from PersonAnswer", new CountMapper())
                .stream().findAny().orElse(null);
    }

    public Count countAllTrueResponses() {
        return jdbcTemplate.query("select count(answer_id) from Answer join PersonAnswer on Answer.id = PersonAnswer.ANSWER_ID where flag=true", new CountMapper())
                .stream().findAny().orElse(null);
    }

    public List<Count> countTrueResponsesWithPerson() {
        return jdbcTemplate.query("select person_id,count(answer_id) from Answer join PersonAnswer on Answer.id = PersonAnswer.ANSWER_ID where flag=true  group by person_id order by count(answer_id)"
                , new PersonCountMapper());
    }

    public List<Count> countFalseResponsesWithQuestion() {
        return jdbcTemplate.query("select question_id,count(answer_id) from ANSWER join PERSONANSWER P2 on ANSWER.ID = P2.ANSWER_ID where flag=false group by question_id order by count(answer_id) desc",
                new QuestionCountMapper());
    }
}
