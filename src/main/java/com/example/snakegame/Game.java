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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.Point;
import java.io.File;


public class Game extends Application {

    private static final int WIDTH = 825;
    private static final int HEIGHT = 600;
    private static final int SQUARE_SIZE = 25;
    private static final int ROWS = WIDTH / SQUARE_SIZE;
    private static final int COLUMNS = HEIGHT / SQUARE_SIZE;
    private Direction currentDirection = Direction.RIGHT;
    static Difficulty difficulty = Difficulty.EASY;
    public Duration speed = Duration.seconds(0.0);
    private GraphicsContext gc;
    private boolean gameOver;
    Snake snake = new Snake();
    Food food = new Food();
    Map map = new Map();
    Score score = new Score();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(System.getProperty("user.dir")+"\\src\\main\\resources\\com\\example\\snakegame\\img\\about.png"));
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        switch (difficulty){
            case EASY ->
                    speed = Duration.millis(110);
            case MEDIUM ->
                    speed = Duration.millis(100);
            case HARD ->
                    speed = Duration.millis(90);
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

    /**
     * <p>Draw the graphic content for every milisecond</p>
     * @param gc GraphicContent
     */
    private void run(GraphicsContext gc) {
        if (gameOver) {
            gc.setFill(Color.RED);
            gc.setFont(new Font("Digital-7", 70));
            //gc.fillText("Game Over \n Please Enter for the close the game", WIDTH / 3.5, HEIGHT / 2);
            return;
        }
        drawBackground(gc);
        map.renderAllBarriers(gc);
        food.drawFood(gc,SQUARE_SIZE);
        snake.drawSnake(gc,SQUARE_SIZE,currentDirection);
        score.drawScore(gc);


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
        food.eatFood(ROWS,COLUMNS,snake,map,score);
        map.changeMap(score);
        if(map.isThereBarrier(food.foodX, food.foodY)){food.generateFood(ROWS, COLUMNS , snake, map);}
    }

    /**
     *<p>This method draw the background</p>
     * @param gc GraphicContent
     */
    private void drawBackground(GraphicsContext gc) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web("#2F4F4F"));
                    //gc.setFill(Color.web("AAD751"));
                } else {
                    gc.setFill(Color.web("#696969"));
                    //gc.setFill(Color.web("A2D149"));
                }
                gc.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    /**
     * <p>This method check the snake situation is it game over ?</p>
     */
    public void gameOver() {

        if (map.isThereBarrier(snake)) {
            // Yılan hit the barrier. Kill them
            Media media = new Media(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\example\\snakegame\\sounds\\game_over.mp3").toURI().toString());
            MediaPlayer player = new MediaPlayer(media);
            player.play();
            gameOver=true;
        }

        //destroy itself
        for (int i = 1; i < snake.snakeBody.size(); i++) {
            if (snake.snakeHead.x == snake.snakeBody.get(i).getX() && snake.snakeHead.getY() == snake.snakeBody.get(i).getY()) {
                Media media = new Media(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\com\\example\\snakegame\\sounds\\game_over.mp3").toURI().toString());
                MediaPlayer player = new MediaPlayer(media);
                player.play();
                gameOver = true;
                break;
            }
        }
    }

    /**
     * <p>This method add to teleport wall to across wall properties.</p>
     */
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


    /**
     * <p>starts start method of Application</p>
     */
    public static void main() {
        launch();
    }
}