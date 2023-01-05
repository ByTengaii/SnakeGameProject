package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Main{
    @FXML
    Label label;

    Text text = new Text();
    Font font = Font.loadFont("C:\\Users\\ÖZHÖLÇEK\\IdeaProjects\\SnakeGame" +
            "\\src\\main\\resources\\com\\example\\snakegame\\Eyvindur.ttf", 35);

    @Override
    public void init() throws Exception {
        text.setText("SNAKE GAME");
        text.setFont(font);
        label.setFont(font);
    }



    @FXML
    public void playPush(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        Game game = new Game();
        game.start(stage);
    }

    @FXML
    public void settingPush(ActionEvent event)throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene settingScene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(settingScene);
        stage.show();
    }

    @FXML
    public void exitPush(ActionEvent event)throws IOException {
        System.exit(0);

    }

}
