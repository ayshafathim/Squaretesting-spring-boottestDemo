package com.example.SpringBootTestDemo.service;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> getAllStudent();

    Student getStudentById(Integer id) throws StudentNotFoundException;

    Student deleteStudentById(Integer id) throws StudentNotFoundException;

    List<Student> getStudentByName(String name);
}
