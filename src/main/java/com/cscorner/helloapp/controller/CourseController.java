package com.cscorner.helloapp.controller;

import com.cscorner.helloapp.model.Course;
import com.cscorner.helloapp.service.CourseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    //  Get course by id
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    //  Create course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    //  Add students to course
    @PostMapping("/{courseId}/students")
    public Course addStudentsToCourse(@PathVariable int courseId,
        @RequestBody List<Integer> studentIds) {
        return courseService.addStudentsToCourse(courseId, studentIds);
    }

    //  Update course
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id,
        @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    //  Delete course
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }
}
