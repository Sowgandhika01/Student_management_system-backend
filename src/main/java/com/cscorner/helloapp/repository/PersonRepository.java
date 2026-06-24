package com.cscorner.helloapp.repository;

import com.cscorner.helloapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}