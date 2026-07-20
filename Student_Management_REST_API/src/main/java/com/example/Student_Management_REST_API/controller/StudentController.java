package com.example.Student_Management_REST_API.controller;

import com.example.Student_Management_REST_API.entity.Student;
import com.example.Student_Management_REST_API.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.add(student);// returns what had been recorded !, returning entity
    }
    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAll();// returning list
    }
    @PutMapping("/{id}")
    public String updateStudent(@RequestBody Student student,@PathVariable long id){
        studentService.update(student,id);
        return "Record updated successfully !";// returning String message
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(long id){
        Student studentResponse = studentService.getById(id);
        if(studentResponse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);// here returning response entity
    }
    @DeleteMapping("/{id}")
    public String deletebyId(long id){
        return studentService.deleteById(id);
    }
}
