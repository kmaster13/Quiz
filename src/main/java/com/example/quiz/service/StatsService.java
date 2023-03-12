package com.example.quiz.service;


import com.example.quiz.dao.StatsDAO;
import com.example.quiz.model.Count;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    @Autowired
    private final StatsDAO statsDAO;

    public int countAll() {
        return statsDAO.countAllResponses().getCount();
    }

    public int countAllTrue() {
        return statsDAO.countAllTrueResponses().getCount();
    }

    public Count personsAndCountMaxTrueResponses() {
        List<Count> list = statsDAO.countTrueResponsesWithPerson();
        return list.get(list.size() - 1);
    }

    public Count personsAndCountMinTrueResponses() {
        return statsDAO.countTrueResponsesWithPerson().get(0);
    }

    public Count questionAndCountWithFalseResponses() {
        return statsDAO.countFalseResponsesWithQuestion().get(0);
    }
}