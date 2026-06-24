package com.cscorner.helloapp.controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.cscorner.helloapp.model.Person;
import com.cscorner.helloapp.service.PersonService;



@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }
}