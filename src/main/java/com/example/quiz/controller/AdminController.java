package com.example.quiz.controller;

import com.example.quiz.model.Answer;
import com.example.quiz.model.Question;
import com.example.quiz.service.AnswerService;
import com.example.quiz.service.PersonService;
import com.example.quiz.service.QuestionService;
import com.example.quiz.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-page")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final AnswerService answerService;

    @Autowired
    private final StatsService statsService;

    @Autowired
    private final PersonService personService;

    @GetMapping
    public String showAllQuestions(Model model) {
        model.addAttribute("questions", questionService.showAllQuestions());
        model.addAttribute("countAllResponses", statsService.countAll());
        model.addAttribute("countAllTrueResponses", statsService.countAllTrue());
        model.addAttribute("personWithMaxCountTrueResponses", personService.findById(statsService.personsAndCountMaxTrueResponses().getPersonId()));
        model.addAttribute("countPersonMaxTrueResponses", statsService.personsAndCountMaxTrueResponses().getCount());
        model.addAttribute("personWithMinCountTrueResponses", personService.findById(statsService.personsAndCountMinTrueResponses().getPersonId()));
        model.addAttribute("countPersonMinTrueResponses", statsService.personsAndCountMinTrueResponses().getCount());
        model.addAttribute("hardQuestion", questionService.showQuestion(statsService.questionAndCountWithFalseResponses().getQuestionId()));
        model.addAttribute("countFalseResponsesInHardQuestion", statsService.questionAndCountWithFalseResponses().getCount());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String showQuestion(@PathVariable("id") long id, Model model) {
        model.addAttribute("question", questionService.showQuestion(id));
        model.addAttribute("answers", answerService.showAnswersByQuestion(id));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newQuestion(@ModelAttribute("question") Question question) {
        return "admin/new";
    }

    @PostMapping
    public String createQuestion(@ModelAttribute("question") Question question) {
        questionService.createQuestion(question);
        return "redirect:/admin-page";
    }

    @GetMapping("/{id}/new_answer")
    public String newAnswer(@PathVariable("id") long id, Model model, @ModelAttribute("answer") Answer answer) {
        model.addAttribute("question", questionService.showQuestion(id));
        return "admin/new_answer";
    }

    @PostMapping("/{id}/new_answer")
    public String createAnswer(@ModelAttribute("answer") Answer answer, @PathVariable("id") long id) {
        answerService.createAnswer(answer, id);
        return "redirect:/admin-page/{id}";
    }

    @GetMapping("/{id}/edit")
    public String editQuestion(Model model, @PathVariable("id") long id) {
        model.addAttribute("question", questionService.showQuestionWithAnswers(id));
        model.addAttribute("ans", new Answer());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String editQuestion(@ModelAttribute("question") Question question, @PathVariable("id") long id) {
        questionService.updateQuestion(question);
        return "redirect:/admin-page/{id}";
    }

    @GetMapping("/{id}/{aId}/edit_answer")
    public String editAnswer(Model model, @PathVariable("id") long id, @PathVariable("aId") long aId) {
        model.addAttribute("question", questionService.showQuestion(id));
        model.addAttribute("answer", answerService.showAnswer(aId));
        return "admin/edit_answer";
    }

    @PatchMapping("/{id}/{aId}/edit_answer")
    public String editAnswer(@ModelAttribute("answer") Answer answer, @PathVariable("id") long id, @PathVariable("aId") long aId) {
        answerService.editAnswer(answer, aId);
        return "redirect:/admin-page/{id}";
    }

    @DeleteMapping("/{id}/{aId}")
    public String deleteAnswer(@PathVariable("id") long id, @PathVariable("aId") long aId) {
        answerService.deleteAnswer(aId);
        return "redirect:/admin-page/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable("id") long id) {
        questionService.deleteQuestion(id);
        return "redirect:/admin-page";
    }
}