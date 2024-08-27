package com.example.demo.exceptions;

public class ProjectNotFoundException  extends  RuntimeException{

    ProjectNotFoundException(){
        super("project not found");
    }
}
