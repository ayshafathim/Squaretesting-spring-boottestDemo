package com.example.SpringBootTestDemo.controller;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {
    private final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;

    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        LOGGER.info("Inside StudentController addStudent method,RequestBody :{}", student);
        return studentService.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();

    }

    @GetMapping("/{student-id}")
    public Student GetStudentById(@PathVariable("student-id") Integer id) {
        return studentService.getStudentById(id);
    }

    @DeleteMapping("/{student-id}")
    public Student DeleteStudentById(@PathVariable("student-id") Integer id) {
        return studentService.deleteStudentById(id);
    }

    @GetMapping("/name/{student-name}")
    public List<Student> getStudentByName(@PathVariable("student-name") String name) {
        return studentService.getStudentByName(name);

    }

}
