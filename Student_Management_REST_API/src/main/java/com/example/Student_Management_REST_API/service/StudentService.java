package com.example.Student_Management_REST_API.service;

import com.example.Student_Management_REST_API.entity.Student;
import com.example.Student_Management_REST_API.exception.DuplicateEmailException;
import com.example.Student_Management_REST_API.exception.InvalidAgeException;
import com.example.Student_Management_REST_API.exception.StudentNotFoundException;
import com.example.Student_Management_REST_API.repository.StudentRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // add method
    public Student add(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new DuplicateEmailException("Email already exists: " + student.getEmail());
        }
        if (student.getAge() < 16) {
            throw new InvalidAgeException("Age must be at least 16. Provided: " + student.getAge());
        }
        return studentRepository.save(student);
    }
    // get all method
    public List<Student> getAll(){
        return studentRepository.findAll();
    }
    // update method
    public Student update(Student student, long id){
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty()){
            return null;
        }
        Student studentToSave = existingStudent.get();

        studentToSave.setFullName(student.getFullName());
        studentToSave.setAge(student.getAge());
        studentToSave.setCourse(student.getCourse());
        studentToSave.setEmail(student.getEmail());

        return studentRepository.save(studentToSave);
    }
    // get by id
    public Student getById(long id){
        //return studentRepository.findById(id).orElse(null);
        if(!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Student ID doesn't exist in DB, Provided : "+id);
        }
        return studentRepository.findById(id).orElse(null);
    }
    // delete by id
    public void deleteById(long id){
//        if(!studentRepository.existsById(id)){
//            throw new StudentNotFoundException("Id doesn't exist !");
//        }
//        studentRepository.deleteById(id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Id doesn't exist !")
            );

        studentRepository.delete(student);
    }
}
