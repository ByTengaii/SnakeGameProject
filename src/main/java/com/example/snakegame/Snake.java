package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    public Point snakeHead;
    public List<Point> snakeBody = new ArrayList();

    String projectPath = System.getProperty("user.dir");
    Image headImage = new Image(projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\snakeHead.png");
    Image bodyImage = new Image(projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\snakeBody.png");
    ImageView imageViewSnakeHead = new ImageView(headImage);
    public void drawSnake(GraphicsContext gc, int SQUARE_SIZE) {
        // Yılanın kafası için resim kullan
        imageViewSnakeHead.setRotate(180);

        gc.drawImage(imageViewSnakeHead.getImage(), snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE);

        // Yılanın vücudu için resim kullan
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
