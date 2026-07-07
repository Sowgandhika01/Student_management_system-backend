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

// @Service
// public class StudentService {
//     private final StudentRepository repo;

//     public StudentService(StudentRepository repo) {
//         this.repo = repo;
//     }

//     @CacheEvict(value = "studentList", allEntries = true)
//     public Student saveStudent(Student s) {
//         return repo.save(s);
//     }

//     @Cacheable("studentList")
//     @Transactional
//     public List<Student> getAllStudents() {
//         System.out.println("Fetching all students from DB...");
//         return repo.findAll();
//     }

//     @Cacheable(value = "student", key = "#id")
//     public Student getStudentById(int id) {
//         System.out.println("Fetching student from DB...");
//         return repo.findById(id).orElse(null);
//     }

//     @CachePut(value = "student", key = "#id")
//     @CacheEvict(value = "studentList", allEntries = true)
//     public Student updateStudent(int id, Student s) {

//         Student existing = repo.findById(id).orElse(null);

//         if (existing == null) {
//             return null;
//         }

//         existing.setName(s.getName());
//         existing.setEmail(s.getEmail());

//         return repo.save(existing);
//     }

//     @Caching(evict = {
//         @CacheEvict(value = "student", key = "#id"),
//         @CacheEvict(value = "studentList", allEntries = true)
//     })
//     public void deleteStudent(int id) {
//         repo.deleteById(id);
//     }
// }