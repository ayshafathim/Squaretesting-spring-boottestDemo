package com.example.SpringBootTestDemo.exception;

public class StudentNotFoundException extends RuntimeException{
    private static final long serialVersionID=1l;
    public StudentNotFoundException(String message){
        super(message);
    }
}
