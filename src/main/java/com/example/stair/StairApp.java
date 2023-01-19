package com.example.stair;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.BiConsumer;

public class StairApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StairApp.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);
        Image icon = new Image("stair_l.png");
        stage.getIcons().add(icon);
        stage.setTitle("ЛЕСЕНКА");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}