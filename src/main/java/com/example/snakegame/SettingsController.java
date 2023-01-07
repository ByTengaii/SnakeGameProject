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
     * <p>Changes game difficulty to EASY, when click Easy button</p>
     */
    @FXML
    public void easyPush() {
        Game.difficulty = Difficulty.EASY;
    }

    /**
     * <p>Changes game difficulty to MEDIUM, when click Medium button</p>
     */
    @FXML
    public void mediumPush() {
        Game.difficulty = Difficulty.MEDIUM;
    }

    /**
     * <p>Changes game difficulty to MEDIUM, when click Hard button</p>
     */
    @FXML
    public void hardPush() {
        Game.difficulty = Difficulty.HARD;
    }

    /**
     * <p>Closes Setting window and opens Menu window</p>
     * @param event Used to close the Setting Menu
     */
    @FXML
    public void returnPush(ActionEvent event) {
        Parent menu = null;
        try {
            menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene exit = new Scene(menu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(exit);
        stage.show();
    }
}
