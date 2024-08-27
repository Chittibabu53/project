package com.example.demo.services;


import com.example.demo.models.Project;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }


    public List<Project> getAllProjects() {
        if(projectRepository.findAll().isEmpty()){
            throw  new RuntimeException("projects not found");
        }
        else{
            return projectRepository.findAll();
        }

    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(()->new RuntimeException("project not found"));
    }

    public void deleteProject(Long projectId) {
        Project project=projectRepository.findById(projectId).orElseThrow(()->new RuntimeException("project not found"));
        projectRepository.deleteById(projectId);
    }
}
