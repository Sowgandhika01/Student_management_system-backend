package com.cscorner.helloapp.service;

import com.cscorner.helloapp.model.Marks;
import com.cscorner.helloapp.model.Student;
import com.cscorner.helloapp.repository.MarksRepository;
import com.cscorner.helloapp.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarksService {

    private final MarksRepository marksRepository;

    private final StudentRepository studentRepository;

    public MarksService(MarksRepository marksRepository, StudentRepository studentRepository) {
        this.marksRepository = marksRepository;
        this.studentRepository = studentRepository;
    }

    // Get all marks
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    // Get mark by id
    public Marks getMarkById(int id) {
        return marksRepository.findById(id).orElse(null);
    }

    // Create marks and link to student
    public Marks createMark(int studentId, Marks marks) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            // Important: set student
            marks.setStudent(student);

            return marksRepository.save(marks);
        } else {
            throw new RuntimeException("Student not found with id: " + studentId);
        }
    }

    // Update marks
    public Marks updateMarks(int id, Marks updatedMarks) {

        Optional<Marks> optionalMarks = marksRepository.findById(id);

        if (optionalMarks.isPresent()) {
            Marks marks = optionalMarks.get();

            marks.setSubject(updatedMarks.getSubject());
            marks.setMarks(updatedMarks.getMarks());

            return marksRepository.save(marks);
        } else {
            return null;
        }
    }

    // Delete marks
    public String deleteMarks(int id) {
        marksRepository.deleteById(id);
        return "Mark deleted successfully";
    }
}