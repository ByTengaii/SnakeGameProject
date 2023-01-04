package com.example.snakegame;

import com.example.snakegame.enums.Difficulty;
import com.example.snakegame.enums.Direction;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Game extends Application {

    private static final int WIDTH = 825;
    private static final int HEIGHT = 600;
    private static final int SQUARE_SIZE = 25;
    private static final int ROWS = WIDTH / SQUARE_SIZE;
    private static final int COLUMNS = HEIGHT / SQUARE_SIZE;
    private Direction currentDirection = Direction.RIGHT;
    static Difficulty difficulty = Difficulty.EASY;
    private Duration speed = Duration.seconds(0.0);
    private GraphicsContext gc;
    private boolean gameOver;
    Snake snake = new Snake();
    Food food = new Food();
    Map map = new Map();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        switch (difficulty){
            case EASY ->
                    speed = Duration.millis(110);
            case MEDIUM ->
                    speed = Duration.millis(90);
            case HARD ->
                    speed = Duration.millis(70);
        }

        //Read the input
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != Direction.LEFT) {
                        currentDirection = Direction.RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != Direction.RIGHT) {
                        currentDirection = Direction.LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != Direction.DOWN) {
                        currentDirection = Direction.UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != Direction.UP) {
                        currentDirection = Direction.DOWN;
                    }
                }
            }
        });

        for (int i = 0; i < 3; i++) {
            snake.snakeBody.add(new Point(5, 5));
        }
        snake.snakeHead = snake.snakeBody.get(0);
        food.generateFood(ROWS,COLUMNS,snake,map);

        Timeline timeline = new Timeline(new KeyFrame(speed, e -> run(gc)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void run(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            gc.fillText("Game Over", WIDTH / 3.5, HEIGHT / 2);
            return;
        }
        drawBackground(gc);
        renderAllBarriers(gc);
        food.drawFood(gc,SQUARE_SIZE);
        snake.drawSnake(gc,SQUARE_SIZE,currentDirection);
        food.drawScore(gc);


        for (int i = snake.snakeBody.size() - 1; i >= 1; i--) {
            snake.snakeBody.get(i).x = snake.snakeBody.get(i - 1).x;
            snake.snakeBody.get(i).y = snake.snakeBody.get(i - 1).y;
        }

        switch (currentDirection) {
            case RIGHT:
                snake.moveRight();
                break;
            case LEFT:
                snake.moveLeft();
                break;
            case UP:
                snake.moveUp();
                break;
            case DOWN:
                snake.moveDown();
                break;
        }
        WalltoWall();
        gameOver();
        food.eatFood(ROWS,COLUMNS,snake,map);
        changeMap();
    }

    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public void gameOver() {

        //Hit the window
        /*
        if (snake.snakeHead.x < 0 || snake.snakeHead.y < 0 || snake.snakeHead.x * SQUARE_SIZE >= WIDTH || snake.snakeHead.y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }
         */

        if (isThereBarrier()) {
            // Yılan engele çarptı, ölmesini sağlayın
            gameOver=true;
        }

        //destroy itself
        for (int i = 1; i < snake.snakeBody.size(); i++) {
            if (snake.snakeHead.x == snake.snakeBody.get(i).getX() && snake.snakeHead.getY() == snake.snakeBody.get(i).getY()) {
                gameOver = false;
                break;
            }
        }
    }

    private void WalltoWall()
    {
        if (snake.snakeHead.y < 0) {
            snake.snakeHead.y = COLUMNS - 1;
        }
        if (snake.snakeHead.y > COLUMNS - 1) {
            snake.snakeHead.y = 0;
        }
        if (snake.snakeHead.x < 0) {
            snake.snakeHead.x = ROWS - 1;
        }
        if (snake.snakeHead.x > ROWS - 1) {
            snake.snakeHead.x = 0;
        }
    }

    private void renderAllBarriers(GraphicsContext gc){
        for (Barrier barrier : map.getMap()){
            barrier.barrierRender(gc);
        }

    }

    private boolean isThereBarrier(){
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


    public void changeMap(){
        if (food.getScore() == 10 && map.getIndex()+1 < map.getMapSize()){
            map.setIndex(map.getIndex() + 1);
            food.resetScore();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}