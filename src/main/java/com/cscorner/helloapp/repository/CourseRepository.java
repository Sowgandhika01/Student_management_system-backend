package com.cscorner.helloapp.repository;

import com.cscorner.helloapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
