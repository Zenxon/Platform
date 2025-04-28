package com.k2124884.Platform.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author zenas
 */
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class HomeController {
    @FXML
    private void showNotifications() throws IOException {
        MainController.getInstance().showNotifications();
    }
    @FXML
    private void showLibrary() throws IOException {
        MainController.getInstance().showLibrary();
    }
}