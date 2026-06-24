package com.cscorner.helloapp.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.cscorner.helloapp.model.Person;
import com.cscorner.helloapp.repository.PersonRepository;

@Service
public class PersonService {
    
 private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person savePerson(Person person) {
        // business logic can go here
        if (person == null) {
            throw new IllegalArgumentException("Person cannot be null");
        }
        return personRepository.save(person);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    
}
