/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.service;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.Notification;
import com.k2124884.Platform.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    public List<Notification> getNotificationsForUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}