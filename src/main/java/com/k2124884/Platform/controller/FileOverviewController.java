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
import com.k2124884.Platform.model.VersionHistory;
import com.k2124884.Platform.service.MusicFileService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileOverviewController {
    @FXML private Label fileImage;
    @FXML private TableView<VersionHistory> versionTable;
    @FXML private TableColumn<VersionHistory, String> versionNumberColumn;
    @FXML private TableColumn<VersionHistory, String> editNameColumn;
    @FXML private TableColumn<VersionHistory, LocalDateTime> editTimeColumn;
    @FXML private TableColumn<VersionHistory, String> commentColumn;
    @FXML private TableColumn<VersionHistory, Void> downloadColumn;
    @Autowired private MusicFileService musicFileService;
    @Autowired private RestTemplate restTemplate; // Added for download
    private Long fileId;

    public void setFileId(Long fileId) {
        this.fileId = fileId;
        initialize();
    }

    @FXML
    private void initialize() {
        versionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("versionNumber"));
        editNameColumn.setCellValueFactory(new PropertyValueFactory<>("editName"));
        editTimeColumn.setCellValueFactory(new PropertyValueFactory<>("editTime"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
        downloadColumn.setCellFactory(col -> new TableCell<>() {
            private final Button downloadButton = new Button("Download");
            {
                downloadButton.setOnAction(event -> {
                    VersionHistory version = getTableRow().getItem();
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Save Version " + version.getVersionNumber());
                    fileChooser.setInitialFileName("version_" + version.getVersionNumber() + ".file");
                    java.io.File saveFile = fileChooser.showSaveDialog(getTableView().getScene().getWindow());
                    if (saveFile != null) {
                        try {
                            ResponseEntity<byte[]> response = restTemplate.getForEntity(
                                "http://localhost:8080/music/download/" + fileId, byte[].class);
                            if (response.getBody() != null) {
                                Files.write(saveFile.toPath(), response.getBody());
                            } else {
                                System.err.println("Download failed: No content received.");
                            }
                        } catch (IOException e) {
                            System.err.println("Error writing file: " + e.getMessage());
                            e.printStackTrace();
                        } catch (Exception e) {
                            System.err.println("Error downloading file: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : downloadButton);
            }
        });

        if (fileId != null) {
            MusicFile musicFile = musicFileService.getMusicFileById(fileId);
            if (musicFile != null) {
                versionTable.getItems().setAll(musicFile.getVersionHistories());
            } else {
                // Load sample data if no file is found
                versionTable.getItems().setAll(createSampleVersionHistory());
            }
        }
    }

    private List<VersionHistory> createSampleVersionHistory() {
        List<VersionHistory> sampleVersions = new ArrayList<>();
        
        // Sample version 1
        VersionHistory v1 = new VersionHistory();
        v1.setVersionNumber("1.0");
        v1.setEditName("John Doe");
        v1.setEditTime(LocalDateTime.now().minusDays(2));
        v1.setComment("Initial upload");
        sampleVersions.add(v1);
        
        // Sample version 2
        VersionHistory v2 = new VersionHistory();
        v2.setVersionNumber("1.1");
        v2.setEditName("Jane Smith");
        v2.setEditTime(LocalDateTime.now().minusDays(1));
        v2.setComment("Added new section");
        sampleVersions.add(v2);
        
        // Sample version 3
        VersionHistory v3 = new VersionHistory();
        v3.setVersionNumber("1.2");
        v3.setEditName("John Doe");
        v3.setEditTime(LocalDateTime.now().minusHours(6));
        v3.setComment("Fixed mixing issues");
        sampleVersions.add(v3);
        
        return sampleVersions;
    }
}