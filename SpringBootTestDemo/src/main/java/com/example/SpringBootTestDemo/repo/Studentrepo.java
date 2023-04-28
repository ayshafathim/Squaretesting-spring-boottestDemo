package com.example.SpringBootTestDemo.repo;

import com.example.SpringBootTestDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Studentrepo extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
}
