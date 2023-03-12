package com.example.quiz.dao;

import com.example.quiz.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createOrUpdate(Person person) {
        Person pers = jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{person.getEmail()},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        if(pers == null){
            jdbcTemplate.update("INSERT INTO Person(email) VALUES(?)", person.getEmail());
        }
    }

    public Person findByEmail(String email){
        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person findById(long id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}