/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.service;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.MusicFile;
import com.k2124884.Platform.model.Notification;
import com.k2124884.Platform.model.VersionHistory;
import com.k2124884.Platform.repository.MusicFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@Service
public class MusicFileService {
    @Autowired
    private MusicFileRepository musicFileRepository;
    @Autowired
    private NotificationService notificationService;
    public List<MusicFile> getAllMusicFiles() {
        return musicFileRepository.findAll();
    }
    public List<MusicFile> getMusicFilesByProjectId(Long projectId) {
        return musicFileRepository.findByProjectId(projectId);
    }
    public MusicFile getMusicFileById(Long id) {
        return musicFileRepository.findById(id).orElse(null);
    }
    public MusicFile saveMusicFile(MusicFile musicFile, Long userId) {
        MusicFile savedFile = musicFileRepository.save(musicFile);
        // Add to version history
        VersionHistory version = new VersionHistory();
        version.setVersionNumber(musicFile.getVersionNumber());
        version.setEditName(musicFile.getLastEditName());
        version.setEditTime(musicFile.getLastEditTime());
        version.setComment("File uploaded");
        savedFile.getVersionHistories().add(version);
        musicFileRepository.save(savedFile);
        // Create notification
        Notification notification = new Notification();
        notification.setMessage("File " + musicFile.getFileName() + " was uploaded to project " + musicFile.getProjectId());
        notification.setTimestamp(LocalDateTime.now());
        notification.setUserId(userId);
        notificationService.saveNotification(notification);
        return savedFile;
    }
}