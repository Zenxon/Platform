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
   import com.k2124884.Platform.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @Autowired private UserService userService;
    private Long loggedInUserId;
    @FXML
    private void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (userService.validateUser(username, password)) {
            loggedInUserId = userService.getUserByUsername(username).getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainLayout.fxml"));
            loader.setControllerFactory(controllerClass -> PlatformApplication.getApplicationContext().getBean(controllerClass));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            MainController mainController = loader.getController();
            mainController.showHome();
        } else {
            usernameField.setText("Invalid credentials");
        }
    }
    public Long getLoggedInUserId() {
        return loggedInUserId;
    }
}
