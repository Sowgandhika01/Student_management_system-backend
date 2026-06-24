package com.cscorner.helloapp.repository;

import com.cscorner.helloapp.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
}