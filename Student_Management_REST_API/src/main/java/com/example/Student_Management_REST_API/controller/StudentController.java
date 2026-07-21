package com.example.Student_Management_REST_API.controller;

import com.example.Student_Management_REST_API.entity.Student;
import com.example.Student_Management_REST_API.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
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
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        Student savedStudent = studentService.add(student);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedStudent);
    }

    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAll();// returning list
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable long id){
        Student studentResp =  studentService.update(student,id);
        if(studentResp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable long id){
        Student studentResponse = studentService.getById(id);
        if(studentResponse == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(studentResponse);// here returning response entity
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletebyId(@PathVariable long id){
        studentService.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully from record !");// returning String message
    }
}
