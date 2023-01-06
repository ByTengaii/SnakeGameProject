package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    public static MediaPlayer player;

    /**
     * <p>The start method of Application class opens Menu window</p>
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
	@Override
    public void start(Stage primaryStage) throws Exception {
        Media media = new Media(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\example\\snakegame\\sounds\\background.mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.play();

        Pane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\example\\snakegame\\img\\about.png"));
        primaryStage.show();
    }

    /**
     *
     */
    public static void main() {
        launch();
    }
}
