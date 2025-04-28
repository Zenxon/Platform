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
@Entity
public class VersionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String versionNumber;
    private String editName;
    private LocalDateTime editTime;
    private String comment;
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVersionNumber() { return versionNumber; }
    public void setVersionNumber(String versionNumber) { this.versionNumber = versionNumber; }
    public String getEditName() { return editName; }
    public void setEditName(String editName) { this.editName = editName; }
    public LocalDateTime getEditTime() { return editTime; }
    public void setEditTime(LocalDateTime editTime) { this.editTime = editTime; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}