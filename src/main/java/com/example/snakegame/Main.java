package com.example.snakegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	
    public void start(Stage primaryStage) throws Exception {
        //Font.loadFont(Font.loadFont(Object.class.getResource("C:\\Users\\ÖZHÖLÇEK\\IdeaProjects\\SnakeGame\\src\\main\\resources\\com\\example\\snakegame\\Eyvindur.ttf").toExternalForm(), 10));
        Pane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
        //scene.getStylesheets().add(String.valueOf(getClass().getResource("C:\\Users\\ÖZHÖLÇEK\\IdeaProjects\\SnakeGame\\src\\main\\resources\\com\\example\\snakegame\\Eyvindur.ttf")));
        scene.getStylesheets().add("menu.css");
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
