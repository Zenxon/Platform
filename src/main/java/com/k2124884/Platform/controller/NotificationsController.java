/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.k2124884.Platform.controller;

/**
 *
 * @author zenas
 */
import com.k2124884.Platform.model.Notification;
import com.k2124884.Platform.service.NotificationService;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class NotificationsController {
    @FXML private ListView<String> notificationList;
    @Autowired private NotificationService notificationService;
    @FXML
    private void initialize() {
        // Replace with actual user ID (e.g., from logged-in user)
        Long userId = 1L;
        notificationList.getItems().setAll(
            notificationService.getNotificationsForUser(userId).stream()
                .map(notification -> notification.getTimestamp() + ": " + notification.getMessage())
                .toList()
        );
    }

    @FXML
    private void goBack() {
        try {
            MainController.getInstance().showHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}