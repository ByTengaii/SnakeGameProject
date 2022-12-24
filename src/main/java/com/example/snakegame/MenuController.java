package com.example.snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController extends Main{
    @FXML
    public void playPush(ActionEvent event) throws Exception {
        Stage stage = new Stage();
        start(stage);
    }

    @FXML
    public void settingPush(ActionEvent event)throws Exception {
        //Pane root = FXMLLoader.load(getClass().getResource());

    }

    @FXML
    public void exitPush(ActionEvent event)throws IOException {
        System.exit(0);

    }

}
