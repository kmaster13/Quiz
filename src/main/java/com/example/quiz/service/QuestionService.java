package com.example.quiz.service;


import com.example.quiz.dao.AnswerDAO;
import com.example.quiz.dao.PersonAnswerDAO;
import com.example.quiz.dao.PersonDAO;
import com.example.quiz.dao.QuestionDAO;
import com.example.quiz.model.Answer;
import com.example.quiz.model.PersonAnswer;
import com.example.quiz.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    @Autowired
    private final QuestionDAO questionDAO;

    @Autowired
    private final AnswerDAO answerDAO;

    @Autowired
    private final PersonAnswerDAO personAnswerDAO;

    public List<Question> showAllQuestions() {
        return questionDAO.index();
    }

    public List<Question> showAllQuestionsWithListForPerson(long id) {

        List<Question> listQuestions = questionDAO.listWithoutResponses(id);

        for (Question question : listQuestions) {
            question.setAnswers(answerDAO.showAnswersByQuestion(question.getId()));
        }
        return listQuestions;
    }

    public Question showQuestion(long id) {
        return questionDAO.show(id);
    }

    public Question showQuestionWithAnswers(long id) {
        Question question = questionDAO.show(id);
        question.setAnswers(answerDAO.showAnswersByQuestion(id));
        return question;
    }

    public void createQuestion(Question question) {
        questionDAO.save(question);
    }

    public void addResponse(Question question, long id) {
        for (Answer answer : question.getAnswers()) {
            if (answer.getId() != null) {
                personAnswerDAO.create(id, answer.getId());
            }
        }
    }

    public void updateQuestion(Question question) {
        questionDAO.update(question);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            answerDAO.updateAnswer(question.getAnswers().get(i));
        }
    }

    public void deleteQuestion(long id) {
        questionDAO.delete(id);
    }
}