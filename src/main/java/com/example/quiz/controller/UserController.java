package com.example.quiz.controller;


import com.example.quiz.model.Person;
import com.example.quiz.model.Question;
import com.example.quiz.service.PersonService;
import com.example.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user-page")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final PersonService personService;

    @GetMapping
    public String auth(Model model) {
        model.addAttribute("person", new Person());
        return "user/auth";
    }

    @PostMapping()
    public String auth(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "user/auth";
        personService.createOrUpdateAndFind(person);
        long id = personService.findIdByLong(person.getEmail());
        return "redirect:/user-page/" + id;
    }


    @GetMapping("/{id}")
    public String index(Model model, @PathVariable("id") long id) {
        model.addAttribute("questions", questionService.showAllQuestionsWithListForPerson(id));
        model.addAttribute("quest", new Question());
        model.addAttribute("person", personService.findById(id));
        return "user/index";
    }

    @PostMapping("/{id}")
    public String create(@ModelAttribute("quest") Question question, @PathVariable("id") long id) {
        questionService.addResponse(question, id);
        return "redirect:/user-page/{id}";
    }
}