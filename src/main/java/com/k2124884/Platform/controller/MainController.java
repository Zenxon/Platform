/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.controller;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.PlatformApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class MainController {
    @FXML private Pane contentArea;
    private static MainController instance;
    public MainController() {
        instance = this;
    }
    public static MainController getInstance() {
        return instance;
    }
    @FXML
    public void showNotifications() throws IOException {
        loadFXML("/fxml/NotificationsScreen.fxml");
    }
    public void showHome() throws IOException {
        loadFXML("/fxml/HomeScreen.fxml");
    }
    public void showLibrary() throws IOException {
        loadFXML("/fxml/LibraryScreen.fxml");
    }
    public void showProjectOverview(Long projectId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProjectOverviewScreen.fxml"));
        loader.setControllerFactory(controllerClass -> PlatformApplication.getApplicationContext().getBean(controllerClass));
        Parent root = loader.load();
        ProjectOverviewController controller = loader.getController();
        controller.setProjectId(projectId);
        contentArea.getChildren().setAll(root);
    }
    public void showFileOverview(Long fileId) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FileOverviewScreen.fxml"));
        loader.setControllerFactory(controllerClass -> PlatformApplication.getApplicationContext().getBean(controllerClass));
        Parent root = loader.load();
        FileOverviewController controller = loader.getController();
        controller.setFileId(fileId);
        contentArea.getChildren().setAll(root);
    }
    private void loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        loader.setControllerFactory(controllerClass -> PlatformApplication.getApplicationContext().getBean(controllerClass));
        Parent root = loader.load();
        contentArea.getChildren().setAll(root);
    }
}