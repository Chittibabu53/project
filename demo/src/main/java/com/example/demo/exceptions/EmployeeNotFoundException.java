package com.example.demo.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException (){
        super("employee not found");
    }

}