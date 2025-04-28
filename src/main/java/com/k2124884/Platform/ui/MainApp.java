package com.k2124884.Platform.ui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.k2124884.Platform.PlatformApplication;
import com.k2124884.Platform.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
public class MainApp extends Application {
    private ConfigurableApplicationContext context;
    @Override
    public void init() {
        context = PlatformApplication.getApplicationContext();
        if (context == null) {
            throw new IllegalStateException("Spring context is not initialized.");
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainLayout.fxml"));
        loader.setControllerFactory(context::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Music Platform");
        stage.setScene(scene);
        MainController mainController = loader.getController();
        mainController.showHome();
        stage.show();
    }
    @Override
    public void stop() {
        PlatformApplication.closeContext();
    }
    public static void main(String[] args) {
        // Initialize Spring context before launching JavaFX
        PlatformApplication.initializeContext(args);
        launch(args);
    }
}