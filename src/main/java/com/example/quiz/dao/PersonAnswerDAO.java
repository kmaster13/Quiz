package com.example.quiz.dao;


import com.example.quiz.model.PersonAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonAnswerDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<PersonAnswer> findByPerson(long id) {
        return jdbcTemplate.query("SELECT answer_id FROM PersonAnswer WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(PersonAnswer.class));
    }

    public void create(long personId, long answerId) {
        jdbcTemplate.update("INSERT INTO PersonAnswer(person_id,answer_id) VALUES(?,?)", personId, answerId);
    }
}
