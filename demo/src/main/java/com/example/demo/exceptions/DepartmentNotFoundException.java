package com.example.demo.exceptions;

public class DepartmentNotFoundException extends RuntimeException{

  public  DepartmentNotFoundException(){
        super("department not found");
    }
}
