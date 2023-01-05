package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {
    static MediaPlayer player;
	@Override
    public void start(Stage primaryStage) throws Exception {
        Media media = new Media(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\example\\snakegame\\sounds\\background.mp3").toURI().toString());
        player = new MediaPlayer(media);
        //player.setStopTime(new Duration(6000));
        player.play();

        Pane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\example\\snakegame\\img\\about.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
