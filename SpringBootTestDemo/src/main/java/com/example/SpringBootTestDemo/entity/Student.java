package com.example.SpringBootTestDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name="ID")

    private Integer id;
    @NotBlank(message="the column name should not be blank")
    @Column(name="NAME")
    private  String name;
    @Column(name="ADDRESS")
    private  String address;
    @Column(name="ROLLNO")
    private  String rollno;


}
