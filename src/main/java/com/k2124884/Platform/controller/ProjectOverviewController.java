/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.controller;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.MusicFile;
import com.k2124884.Platform.service.MusicFileService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.io.IOException;
@Component
public class ProjectOverviewController {
    @FXML private Label projectImage;
    @FXML private TableView<MusicFile> fileTable;
    @FXML private TableColumn<MusicFile, String> fileNameColumn;
    @FXML private TableColumn<MusicFile, String> extensionNameColumn;
    @FXML private TableColumn<MusicFile, String> versionNumberColumn;
    @FXML private TableColumn<MusicFile, String> lastEditNameColumn;
    @FXML private TableColumn<MusicFile, LocalDateTime> lastEditTimeColumn;
    @FXML private TableColumn<MusicFile, Void> downloadColumn;
    @Autowired private MusicFileService musicFileService;
    private Long projectId;
    private Long userId = 1L; // Placeholder; replace with actual user ID
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
        initialize();
    }
    @FXML
    private void initialize() {
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        extensionNameColumn.setCellValueFactory(new PropertyValueFactory<>("extensionName"));
        versionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("versionNumber"));
        lastEditNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastEditName"));
        lastEditTimeColumn.setCellValueFactory(new PropertyValueFactory<>("lastEditTime"));
        downloadColumn.setCellFactory(col -> new javafx.scene.control.TableCell<>() {
    private final Button downloadButton = new Button("Download");
    {
        downloadButton.setOnAction(event -> {
            MusicFile file = getTableRow().getItem();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save File");
            fileChooser.setInitialFileName(file.getFileName());
            java.io.File saveFile = fileChooser.showSaveDialog(getTableView().getScene().getWindow());
            if (saveFile != null) {
                // Simulate download (in a real app, copy the file content)
                System.out.println("Simulating download of " + file.getFileName() + " to " + saveFile.getAbsolutePath());
            }
        });
    }
    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        setGraphic(empty ? null : downloadButton);
    }
});
        fileTable.setOnMouseClicked(event -> {
            MusicFile selectedFile = fileTable.getSelectionModel().getSelectedItem();
            if (selectedFile != null && event.getClickCount() == 2) {
                try {
                    MainController.getInstance().showFileOverview(selectedFile.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (projectId != null) {
            fileTable.getItems().setAll(musicFileService.getMusicFilesByProjectId(projectId));
        }
    }
    @FXML
    private void uploadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File");
        java.io.File file = fileChooser.showOpenDialog(fileTable.getScene().getWindow());
        if (file != null) {
            MusicFile musicFile = new MusicFile();
            musicFile.setFileName(file.getName());
            musicFile.setExtensionName(file.getName().substring(file.getName().lastIndexOf(".") + 1));
            musicFile.setVersionNumber("1.0");
            musicFile.setLastEditName("User"); // Replace with actual user
            musicFile.setLastEditTime(LocalDateTime.now());
            musicFile.setProjectId(projectId);
            musicFileService.saveMusicFile(musicFile, userId);
            fileTable.getItems().add(musicFile);
        }
    }

    @FXML
    private void goBack() {
        try {
            MainController.getInstance().showLibrary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}