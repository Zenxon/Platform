/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.controller;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.Project;
import com.k2124884.Platform.service.ProjectService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class LibraryController {
    @FXML private FlowPane projectGrid;
    @Autowired private ProjectService projectService;
    @FXML
    private void initialize() {
        projectGrid.getChildren().clear();
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            projects = createSampleProjects();
        }
        for (Project project : projects) {
            Button projectButton = new Button(project.getName());
            projectButton.setStyle("-fx-pref-width: 120px; -fx-pref-height: 80px; -fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-alignment: center;");
            projectButton.setOnAction(event -> {
                try {
                    MainController.getInstance().showProjectOverview(project.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            projectGrid.getChildren().add(projectButton);
        }
    }

    private List<Project> createSampleProjects() {
        List<Project> sampleProjects = new ArrayList<>();
        
        // Sample Project 1
        Project p1 = new Project();
        p1.setId(1L);
        p1.setName("Rock Album");
        p1.setThumbnailPath("/images/rock.jpg");
        sampleProjects.add(p1);
        
        // Sample Project 2
        Project p2 = new Project();
        p2.setId(2L);
        p2.setName("Jazz Collection");
        p2.setThumbnailPath("/images/jazz.jpg");
        sampleProjects.add(p2);
        
        // Sample Project 3
        Project p3 = new Project();
        p3.setId(3L);
        p3.setName("Electronic Mix");
        p3.setThumbnailPath("/images/electronic.jpg");
        sampleProjects.add(p3);
        
        // Sample Project 4
        Project p4 = new Project();
        p4.setId(4L);
        p4.setName("Classical Suite");
        p4.setThumbnailPath("/images/classical.jpg");
        sampleProjects.add(p4);
        
        return sampleProjects;
    }
}