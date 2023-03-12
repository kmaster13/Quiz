package com.example.quiz.dao;

import com.example.quiz.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Question> index() {
        return jdbcTemplate.query("SELECT * FROM question", new BeanPropertyRowMapper<>(Question.class));
    }

    public Question show(long id) {
        return jdbcTemplate.query("SELECT * FROM Question WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Question.class))
                .stream().findAny().orElse(null);
    }

    public void save(Question question) {
        jdbcTemplate.update("INSERT INTO Question(title) VALUES(?)", question.getTitle());
    }

    public void update(Question updatedQuestion) {
        jdbcTemplate.update("UPDATE Question SET title=? WHERE id=?", updatedQuestion.getTitle(), updatedQuestion.getId());
    }

    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM Question WHERE id=?", id);
    }

}
