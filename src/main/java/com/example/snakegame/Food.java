package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.File;

import javafx.scene.media.*;

public class Food {
    //Pathway of Desktop
    private String projectPath = System.getProperty("user.dir");
    private String eatSoundPath = projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\sounds\\eat_sound.mp3";
    private MediaPlayer player;
    private final String[] FOODS_IMAGE = new String[]
            {projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_orange.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_apple.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_cherry.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_berry.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_coconut_.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_peach.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_watermelon.png",
                    projectPath+"\\src\\main\\resources\\com\\example\\snakegame\\img\\ic_pomegranate.png"
            };
    private Image foodImage;
    public int x;
    public int y;
    public int score;
    private int SQUARE_SIZE = 25;

    public void generateFood(int ROWS,int COLUMNS , Snake snake, Map map) {
        start:
        while (true) {
            this.x = (int) (Math.random() * ROWS);
            this.y = (int) (Math.random() * COLUMNS);

            for (Point snakeTail : snake.snakeBody) {
                if (snakeTail.getX() == this.x && snakeTail.getY() == this.y) {
                    continue start;
                }

                if (isThereMap(snake, map)){
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    public void drawFood(GraphicsContext gc, int SQUARE_SIZE) {
        gc.drawImage(foodImage, this.x * SQUARE_SIZE, this.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    public void eatFood(int ROWS, int COLUMNS, Snake snake, Map map) {
        if (snake.snakeHead.getX() == this.x && snake.snakeHead.getY() == this.y) {
            snake.snakeBody.add(new Point(-1, -1));
            this.generateFood(ROWS,COLUMNS,snake,map);
            this.score+=5;
            System.out.println(eatSoundPath);
            Media media = new Media(new File(eatSoundPath).toURI().toString());
            player = new MediaPlayer(media);
            player.play();
        }
    }

    public int getScore(){
        return this.score;
    }

    public void resetScore(){
        this.score = 0;
    }

    public void drawScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + this.score, 10, 35);
    }

    public boolean isThereMap(Snake snake , Map map){
        for (Barrier barrier : map.getMap()) {
            if (
                    snake.snakeHead.x*SQUARE_SIZE >= barrier.getX() &&
                            snake.snakeHead.x*SQUARE_SIZE < (barrier.getWIDTH())&&
                            snake.snakeHead.y*SQUARE_SIZE >= barrier.getY() &&
                            snake.snakeHead.y*SQUARE_SIZE < (barrier.getHEIGHT())
            ){
                return true;
            }
        }
        return false;
    }

}
