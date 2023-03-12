package com.example.quiz.dao;


import com.example.quiz.model.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Answer> index() {
        return jdbcTemplate.query("SELECT * FROM Answer", new BeanPropertyRowMapper<>(Answer.class));
    }

    public List<Answer> showAnswersByQuestion(long id) {
        return jdbcTemplate.query("SELECT * FROM Answer WHERE question_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Answer.class));
    }

    public void save(Answer answer) {
        jdbcTemplate.update("INSERT INTO Answer(name,flag,question_id) VALUES(?,?,?)", answer.getName(), answer.getFlag(), answer.getQuestionId());
    }

    public Answer showAnswer(long id) {
        return jdbcTemplate.query("SELECT * FROM Answer WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Answer.class)).stream().findAny().orElse(null);
    }

    public void updateAnswer(Answer answer) {
        jdbcTemplate.update("UPDATE Answer SET name=?, flag=? WHERE id=?", answer.getName(), answer.getFlag(), answer.getId());
    }

    public void deleteAnswer(long id) {
        jdbcTemplate.update("DELETE FROM Answer WHERE id=?", id);
    }
}
