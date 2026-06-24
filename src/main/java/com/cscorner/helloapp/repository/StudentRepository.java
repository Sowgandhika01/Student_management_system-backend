package com.cscorner.helloapp.repository;

import com.cscorner.helloapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}