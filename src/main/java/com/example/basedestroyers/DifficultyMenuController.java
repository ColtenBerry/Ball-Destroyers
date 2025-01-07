package com.example.basedestroyers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class DifficultyMenuController {
    @FXML
    private Pane pane;
    @FXML
    private Button story;
    @FXML
    private Button custom;
    @FXML
    Stage stage;
    Scene scene;
    TotalGame game = TotalGame.getGame();
    @FXML
    private void playStory(ActionEvent event) throws IOException {
        game.setStory(true);
        loadGamePane(event);
    }
    @FXML
    private void playCustom(ActionEvent event) throws IOException {
        game.setStory(false);
        loadGamePane(event);
    }
    private void loadGamePane(ActionEvent event) throws  IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GamePane.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(game.getGameWidth());
        stage.setHeight(game.getGameHeight());
        stage.setX(0);
        stage.setY(0);
        stage.show();
    }
}
