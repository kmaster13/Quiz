package com.example.quiz.service;

import com.example.quiz.dao.AnswerDAO;
import com.example.quiz.model.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {
    @Autowired
    private final AnswerDAO answerDAO;

    public List<Answer> showAnswersByQuestion(long id){
        return answerDAO.showAnswersByQuestion(id);
    }

    public void createAnswer(Answer answer, long id){
        answer.setQuestionId(id);
        answerDAO.save(answer);
    }

    public Answer showAnswer(long id){
        return answerDAO.showAnswer(id);
    }

    public void editAnswer(Answer answer, long id){
        answer.setId(id);
        answerDAO.updateAnswer(answer);
    }

    public void deleteAnswer(long id){
        answerDAO.deleteAnswer(id);
    }
}