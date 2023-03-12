package com.example.quiz.service;


import com.example.quiz.dao.PersonDAO;
import com.example.quiz.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    @Autowired
    private final PersonDAO personDAO;

    public void createOrUpdateAndFind(Person person) {
        personDAO.createOrUpdate(person);
    }

    public long findIdByLong(String email) {
        return personDAO.findByEmail(email).getId();
    }

    public Person findById(long id) {
        return personDAO.findById(id);
    }
}