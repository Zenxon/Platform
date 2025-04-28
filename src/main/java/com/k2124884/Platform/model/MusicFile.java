/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.model;

/**
 *
 * @author zenas
 */
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity
public class MusicFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String extensionName;
    private String versionNumber;
    private String lastEditName;
    private LocalDateTime lastEditTime;
    private Long projectId;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "music_file_id")
    private List<VersionHistory> versionHistories = new ArrayList<>();
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getExtensionName() { return extensionName; }
    public void setExtensionName(String extensionName) { this.extensionName = extensionName; }
    public String getVersionNumber() { return versionNumber; }
    public void setVersionNumber(String versionNumber) { this.versionNumber = versionNumber; }
    public String getLastEditName() { return lastEditName; }
    public void setLastEditName(String lastEditName) { this.lastEditName = lastEditName; }
    public LocalDateTime getLastEditTime() { return lastEditTime; }
    public void setLastEditTime(LocalDateTime lastEditTime) { this.lastEditTime = lastEditTime; }
    public Long getProjectId() { return projectId; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public List<VersionHistory> getVersionHistories() { return versionHistories; }
    public void setVersionHistories(List<VersionHistory> versionHistories) { this.versionHistories = versionHistories; }
}