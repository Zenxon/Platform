/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.service;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.Project;
import com.k2124884.Platform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
}