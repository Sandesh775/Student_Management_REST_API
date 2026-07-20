package com.example.Student_Management_REST_API.repository;

import com.example.Student_Management_REST_API.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
                                                        // <Entity class , Primary Key>
public interface StudentRepository extends JpaRepository<Student,Long>
{

}