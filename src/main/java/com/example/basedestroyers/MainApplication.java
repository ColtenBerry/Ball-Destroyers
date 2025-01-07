package com.example.basedestroyers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    TotalGame game = TotalGame.getGame();
    double width = Screen.getPrimary().getBounds().getWidth();
    double height = Screen.getPrimary().getBounds().getHeight() - 50;
    @Override
    public void start(Stage primaryStage) throws IOException {
        game.setGameWidth(width);
        System.out.println(width);
        System.out.println(height);
        game.setGameHeight(height);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
