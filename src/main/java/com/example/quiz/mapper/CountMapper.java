package com.example.quiz.mapper;

import com.example.quiz.model.Count;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountMapper implements RowMapper<Count> {
    @Override
    public Count mapRow(ResultSet rs, int rowNum) throws SQLException {
        Count count = new Count();
        count.setCount(rs.getInt("count(answer_id)"));
        return count;
    }
}
