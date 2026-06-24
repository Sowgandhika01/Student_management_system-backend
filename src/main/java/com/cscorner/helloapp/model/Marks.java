package com.cscorner.helloapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    private String subject;
    private int marks;

    //Many marks belong to one student
    @ManyToOne
    @JoinColumn(name = "student_id") //foreign key 
    @JsonIgnoreProperties("marksList")
    private Student student;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public int getMarks(){
        return marks;
    }
    public void setMarks(int marks){
        this.marks=marks;
    }
    public Student getStudent(){
        return student;
    }
    public void setStudent(Student student){
        this.student=student;
    }
}
