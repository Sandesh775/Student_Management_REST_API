package com.example.Student_Management_REST_API.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;


@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Student name cannot be blank.")
    private String fullName;
    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Invalid email format")
    private String email;
    @NotNull(message = "Age is required.")
    @Min(value = 0, message = "Age cannot be negative")
    private Integer age;
    @NotBlank
    private String course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
