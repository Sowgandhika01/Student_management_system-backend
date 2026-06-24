package com.cscorner.helloapp.service;

import com.cscorner.helloapp.model.Course;
import com.cscorner.helloapp.model.Student;
import com.cscorner.helloapp.repository.CourseRepository;
import com.cscorner.helloapp.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    //  Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by id
    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    // Create course
    public Course createCourse(Course newCourse) {
        if (newCourse == null) {
            return null;
        }
        return courseRepository.save(newCourse);
    }

    // Add students to course
    public Course addStudentsToCourse(int courseId, List<Integer> studentIds) {
        if (studentIds == null) {
            return null;
        }

        Course course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {
            List<Student> students = studentRepository.findAllById(studentIds);

            // Set relationship from the owning side (Student)
            for (Student student : students) {
                if (student.getCourses() == null) {
                    student.setCourses(new java.util.ArrayList<>());
                }
                if (!student.getCourses().contains(course)) {
                    student.getCourses().add(course);
                }
            }

            studentRepository.saveAll(students);
            return courseRepository.findById(courseId).orElse(null);
        }

        return null;
    }

    // Update course
    public Course updateCourse(int id, Course updatedCourse) {

        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            course.setCourseName(updatedCourse.getCourseName());

            return courseRepository.save(course);
        }

        return null;
    }

    // Delete course
    public String deleteCourse(int id) {
        courseRepository.deleteById(id);
        return "Course deleted successfully";
    }
}
