package com.example.Student_Management_REST_API.service;

import com.example.Student_Management_REST_API.entity.Student;
import com.example.Student_Management_REST_API.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // add method
    public Student add(Student student){
        return studentRepository.save(student);
    }
    // get all method
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    // update method
    public void update(Student student, long id){
        studentRepository.save(student);
    }
    // get by id
    public Student getById(long id){
        return studentRepository.findById(id).orElse(null);
    }
    // delete by id
    public String deleteById(long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student deleted successfully from record !";
        }
        return "Student ID doesn't exist at all !";
    }
}
