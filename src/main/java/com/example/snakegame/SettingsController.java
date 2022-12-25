package com.example.snakegame;

import com.example.snakegame.enums.Difficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsController {
    @FXML
    public void easyPush(ActionEvent event) {
        Game.difficulty = Difficulty.EASY;
    }
    @FXML
    public void mediumPush(ActionEvent event) {
        Game.difficulty = Difficulty.MEDIUM;
    }
    @FXML
    public void hardPush(ActionEvent event) {
        Game.difficulty = Difficulty.HARD;
    }

    @FXML
    public void returnPush(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene exit = new Scene(menu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(exit);
        stage.show();
    }
}
