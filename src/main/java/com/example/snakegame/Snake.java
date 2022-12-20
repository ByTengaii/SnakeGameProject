package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    public Point snakeHead;
    public List<Point> snakeBody = new ArrayList();

    String projectPath = System.getProperty("user.dir");

    public void drawSnake(GraphicsContext gc, int SQUARE_SIZE) {
        // Yılanın kafası için resim kullan
        Image headImage = new Image(projectPath+"\\SnakeGame\\src\\main\\resources\\com\\example\\snakegame\\img\\snakeHead.png");
        gc.drawImage(headImage, snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);

        // Yılanın vücudu için resim kullan
        Image bodyImage = new Image(projectPath+"\\SnakeGame\\src\\main\\resources\\com\\example\\snakegame\\img\\snakeBody.png");
        for (int i = 1; i < snakeBody.size(); i++) {
            gc.drawImage(bodyImage, snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);
        }
    }



    public void moveRight() {
        snakeHead.x++;
    }

    public void moveLeft() {
        snakeHead.x--;
    }

    public void moveUp() {
        snakeHead.y--;
    }

    public void moveDown() {
        snakeHead.y++;
    }
}
