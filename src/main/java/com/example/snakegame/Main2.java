package com.example.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;


public class Main2 extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private GraphicsContext gc;
    private boolean gameOver;
    private int currentDirection;
    Snake snake = new Snake();
    Food food = new Food();

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

        //Read the input
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                    }
                }
            }
        });

        for (int i = 0; i < 3; i++) {
            snake.snakeBody.add(new Point(5, ROWS / 2));
        }
        snake.snakeHead = snake.snakeBody.get(0);
        //food.generateFood(ROWS,COLUMNS,snake);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
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
        Barrier barrier = new Barrier(400,400,SQUARE_SIZE*5,SQUARE_SIZE);
        barrier.barrierRender(gc);
        food.drawFood(gc,SQUARE_SIZE);
        snake.drawSnake(gc,SQUARE_SIZE);
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
        gameOver(barrier);
        //food.eatFood(ROWS,COLUMNS,snake,);
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






    public void gameOver(Barrier barrier) {

        //Hit the window
        if (snake.snakeHead.x < 0 || snake.snakeHead.y < 0 || snake.snakeHead.x * SQUARE_SIZE >= WIDTH || snake.snakeHead.y * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }
        if (
                snake.snakeHead.x*SQUARE_SIZE >= barrier.getX() &&
                snake.snakeHead.x*SQUARE_SIZE < (barrier.getWIDTH())&&
                snake.snakeHead.y*SQUARE_SIZE >= barrier.getY() &&
                snake.snakeHead.y*SQUARE_SIZE < (barrier.getHEIGHT())
        )
        {
            gameOver = true;
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
        if (snake.snakeHead.x < 0) {
            snake.snakeHead.x = COLUMNS - 1;
        }
        if (snake.snakeHead.x > COLUMNS - 1) {
            snake.snakeHead.x = 0;
        }
        if (snake.snakeHead.y < 0) {
            snake.snakeHead.y = ROWS - 1;
        }
        if (snake.snakeHead.y > ROWS - 1) {
            snake.snakeHead.y = 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}