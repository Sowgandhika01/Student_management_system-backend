package com.cscorner.helloapp.controller;

import com.cscorner.helloapp.model.Marks;
import com.cscorner.helloapp.service.MarksService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {

    private final MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    //  Get all marks
    @GetMapping
    public List<Marks> getAllMarks() {
        return marksService.getAllMarks();
    }

    // Get mark by id
    @GetMapping("/{id}")
    public Marks getMarkById(@PathVariable int id) {
        return marksService.getMarkById(id);
    }

    // Create marks for a student
    @PostMapping("/student/{studentId}")
    public Marks createMark(@PathVariable int studentId, @RequestBody Marks marks) {
        return marksService.createMark(studentId, marks);
    }

    //  Update marks
    @PutMapping("/{id}")
    public Marks updateMarks(@PathVariable int id, @RequestBody Marks marks) {
        return marksService.updateMarks(id, marks);
    }

    //  Delete marks
    @DeleteMapping("/{id}")
    public String deleteMarks(@PathVariable int id) {
        return marksService.deleteMarks(id);
    }
}