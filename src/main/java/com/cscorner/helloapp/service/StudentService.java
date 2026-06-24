package com.cscorner.helloapp.service;

import com.cscorner.helloapp.model.Student;
import com.cscorner.helloapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student saveStudent(Student s) {
        if (s == null) {
            return null;
        }
        return repo.save(s);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Student updateStudent(int id, Student s) {
        Student existing = repo.findById(id).orElse(null);
        existing.setName(s.getName());
        existing.setEmail(s.getEmail());
        return repo.save(existing);
    }

    public void deleteStudent(int id) {
        repo.deleteById(id);
    }
}