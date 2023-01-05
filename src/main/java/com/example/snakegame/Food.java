package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.util.Random;

import javafx.util.Duration;

public class Food extends PowerUp{
    //Pathway of Desktop
    String projectPath = System.getProperty("user.dir");
    String imgPath = "\\src\\main\\resources\\com\\example\\snakegame\\img\\";
    private MediaPlayer player;
    private String eatSoundPath = projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\sounds\\eat_sound.mp3";

    private final String[] FOODS_IMAGE = new String[]
            {
                    projectPath+imgPath+"ic_orange.png",
                    projectPath+imgPath+"ic_apple.png",
                    projectPath+imgPath+"ic_cherry.png",
                    projectPath+imgPath+"ic_berry.png",
                    projectPath+imgPath+"ic_coconut_.png",
                    projectPath+imgPath+"ic_peach.png",
                    projectPath+imgPath+"ic_watermelon.png",
                    projectPath+imgPath+"ic_pomegranate.png"
            };
    private Image foodImage;
    public int foodX;
    public int foodY;
    private int SQUARE_SIZE = 25;

    /**
     * <p>Generate cordinates for spawn a food on the map</p>
     * @param ROWS Row numbers of map
     * @param COLUMNS Colum numbers of map
     * @param snake snake object for don't spawn the food on the snake
     * @param map map object that food will spawn on
     */

    public void generateFood(int ROWS,int COLUMNS , Snake snake, Map map) {
        start:
        while (true) {
            this.foodX = (int) (Math.random() * ROWS);
            this.foodY = (int) (Math.random() * COLUMNS);

            for (Point snakeTail : snake.snakeBody) {
                if (snakeTail.getX() == this.foodX && snakeTail.getY() == this.foodY) {
                    continue start;
                }

                if (map.isThereBarrier(foodX,foodY)){
                    continue start;
                }
            }
            setType();
            switch (getType()){
                case CLASSIC -> {
                    foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
                }
                case BONUS_POINT -> {
                    foodImage = new Image(projectPath+imgPath+"POWER_UP.png");
                }
                case CHANGE_SNAKE_SIZE -> {
                    foodImage = new Image(projectPath+imgPath+"new_snakeBody_up.png");
                }
                case WALLS_DEACTIVATED -> {
                    foodImage = new Image(projectPath+imgPath+"WALL_NOTACTIVE.png");
                }
            }

            break;
        }
    }

    /**
     * <p>The food object becomes visible on the map</p>
     * @param gc graphic content
     * @param SQUARE_SIZE Square size of the map.
     */
    public void drawFood(GraphicsContext gc, int SQUARE_SIZE) {
        gc.drawImage(
                foodImage,
                this.foodX * SQUARE_SIZE,
                this.foodY * SQUARE_SIZE,
                SQUARE_SIZE,
                SQUARE_SIZE
        );
    }

    /**
     * <p>When the snake eat a food, snake get point and powerups.
     * This method check snake is eating food or not ?</p>
     * @param ROWS Row numbers of map
     * @param COLUMNS Colum numbers of map
     * @param snake The snake which eat the food
     * @param map The map that playing on
     * @param score score
     */
    public void eatFood(int ROWS, int COLUMNS, Snake snake, Map map,Score score) {
        if (snake.snakeHead.getX() == this.foodX && snake.snakeHead.getY() == this.foodY) {
            switch (getType()){
                case CHANGE_SNAKE_SIZE -> {
                    System.out.println("Shrinked");
                    snake.shrinkSnakeSize();
                    map.setWallSituation(Map.wallStation.ACTIVATED);
                }
                case WALLS_DEACTIVATED -> {
                    System.out.println("Wall");
                    snake.snakeBody.add(new Point(-1, -1));
                    map.setWallSituation(Map.wallStation.DEACTIVATED);
                }
                case BONUS_POINT -> {
                    System.out.println("bonus");
                    Random random = new Random();
                    score.setScore(random.nextInt(5)*5);
                    map.setWallSituation(Map.wallStation.ACTIVATED);
                }
                case CLASSIC -> {
                    System.out.println("classic");
                    snake.snakeBody.add(new Point(-1, -1));
                    score.setScore(5);
                    map.setWallSituation(Map.wallStation.ACTIVATED);
                }

            }
            this.generateFood(ROWS,COLUMNS,snake,map);

            Media media = new Media(new File(eatSoundPath).toURI().toString());
            player = new MediaPlayer(media);
            player.play();
        }
    }

}
