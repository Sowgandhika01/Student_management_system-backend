package com.cscorner.helloapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank
    @Email(message="Email should be valid")
    private String email;

    //One student can have many marks
    @OneToMany(mappedBy ="student", cascade =CascadeType.ALL)
    private List<Marks> marksList;

    
    @ManyToMany
    @JsonIgnoreProperties("students")
    @JoinTable(
        name = "student_course",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;   


    //  Getter for id
    public int getId() {
        return id;
    }

    //  Setter for id
    public void setId(int id) {
        this.id = id;
    }

    //  Getter for name
    public String getName() {
        return name;
    }

    //  Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //  Getter for email
    public String getEmail() {
        return email;
    }

    //  Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
    //  Getter for marksList
    public List<Marks> getMarksList() {
        return marksList;
    }

    //  Setter for marksList
    public void setMarksList(List<Marks> marksList) {
        this.marksList = marksList;
    }

    // Getter for courses
    public List<Course> getCourses() {
        return courses;
    }

    // Setter for courses
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}