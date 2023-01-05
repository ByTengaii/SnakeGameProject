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
    /**
     * <p>Changes game difficulty to EASY</p>
     */
    @FXML
    public void easyPush() {
        Game.difficulty = Difficulty.EASY;
    }

    /**
     * <p>Changes game difficulty to MEDIUM</p>
     */
    @FXML
    public void mediumPush() {
        Game.difficulty = Difficulty.MEDIUM;
    }

    /**
     * <p>Changes game difficulty to MEDIUM</p>
     */
    @FXML
    public void hardPush() {
        Game.difficulty = Difficulty.HARD;
    }

    /**
     * <p></p>
     * @param event
     * @throws IOException
     */
    @FXML
    public void returnPush(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene exit = new Scene(menu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(exit);
        stage.show();
    }
}
