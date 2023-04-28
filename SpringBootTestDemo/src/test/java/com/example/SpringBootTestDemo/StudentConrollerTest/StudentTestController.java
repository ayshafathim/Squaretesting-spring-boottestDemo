package com.example.SpringBootTestDemo.StudentConrollerTest;

import com.example.SpringBootTestDemo.entity.Student;
import com.example.SpringBootTestDemo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class StudentTestController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentservice;
    private Student student;

    @BeforeEach
    void setup() {
        Student student = new Student(1, "Aysha", "kottayam", "245089");
    }

    @Test
    void testSaveStudent() throws Exception {
        Student student = new Student(1, "Aysha", "kottayam", "245089");
        Mockito.when(studentservice.addStudent(student)).thenReturn(student);
        mockMvc.perform(MockMvcRequestBuilders.post("/student/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n" +
                                " \"id\" : 1, \r\n" +
                                " \"name\" : \"Aysha\", \r\n" +
                                " \"address\" :\"kottayam\", \r\n" +
                                " \"rollNo\" : \"245089\" \r\n" + "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}