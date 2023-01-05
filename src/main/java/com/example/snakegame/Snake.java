package com.example.snakegame;

import com.example.snakegame.enums.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake {
    public Point snakeHead;
    public ArrayList<Point> snakeBody = new ArrayList();

    String projectPath = System.getProperty("user.dir");
    private Image headImage;

    /**
     * <p>This method set the snake visible on the map</p>
     * @param gc Graphic Content
     * @param SQUARE_SIZE Square size of the map
     * @param direction Direction that coming from keyboard
     */
    public void drawSnake(GraphicsContext gc, int SQUARE_SIZE, Direction direction) {

        // Create Image objects for each direction
        Image headUp = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeHead_up.png");
        Image headDown = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeHead_down.png");
        Image headLeft = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeHead_left.png");
        Image headRight = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeHead_right.png");
        Image bodyUp = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeBody_up.png");
        Image bodyDown = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeBody_down.png");
        Image bodyLeft = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeBody_left.png");
        Image bodyRight = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeBody_right.png");
        Image bodyUpCornerRight = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeTurn_up-right.png");
        Image bodyUpCornerLeft = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeTurn_up-left.png");
        Image bodyDownCornerRight = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeTurn_down-right.png");
        Image bodyDownCornerLeft = new Image(projectPath + "\\src\\main\\resources\\com\\example\\snakegame\\img\\new_snakeTurn_down-left.png");

        // Select the correct image for the snake's head based on the direction
        if (direction == Direction.UP) {
            headImage = headUp;
        } else if (direction == Direction.DOWN) {
            headImage = headDown;
        } else if (direction == Direction.LEFT) {
            headImage = headLeft;
        } else if (direction == Direction.RIGHT) {
            headImage = headRight;
        }

        // Draw the snake's head
        gc.drawImage(headImage, snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

        // Draw the snake's body
        for (int i = 1; i < snakeBody.size(); i++) {
            Point currentPoint = snakeBody.get(i);
            Point prevPoint = snakeBody.get(i - 1);
            Image bodyImage =null;
            if (i!= (snakeBody.size()-1))
            {
                Point afterPoint = snakeBody.get(i+1);
                //ClockWise
                if (currentPoint.getX() == prevPoint.getX() &&
                        currentPoint.getY() < prevPoint.getY() &&
                        currentPoint.getX() > afterPoint.getX() &&
                        currentPoint.getY() == afterPoint.getY()
                )
                {
                    bodyImage = bodyUpCornerRight;
                }
                else if (currentPoint.getX() < prevPoint.getX() &&
                        currentPoint.getY() == prevPoint.getY() &&
                        currentPoint.getX() == afterPoint.getX() &&
                        currentPoint.getY() < afterPoint.getY()
                )
                {
                    bodyImage = bodyUpCornerLeft;
                }
                else if (currentPoint.getX() == prevPoint.getX() &&
                        currentPoint.getY() > prevPoint.getY() &&
                        currentPoint.getX() < afterPoint.getX() &&
                        currentPoint.getY() == afterPoint.getY()
                )
                {
                    bodyImage = bodyDownCornerLeft;
                }
                else if (currentPoint.getX() > prevPoint.getX() &&
                        currentPoint.getY() == prevPoint.getY() &&
                        currentPoint.getX() == afterPoint.getX() &&
                        currentPoint.getY() > afterPoint.getY()
                )
                {
                    bodyImage = bodyDownCornerRight;
                }

                //AntiClockWise
                else if (currentPoint.getX() > prevPoint.getX() &&
                        currentPoint.getY() == prevPoint.getY() &&
                        currentPoint.getX() == afterPoint.getX() &&
                        currentPoint.getY() < afterPoint.getY()
                )
                {
                    bodyImage = bodyUpCornerRight;
                }
                else if (currentPoint.getX() == prevPoint.getX() &&
                        currentPoint.getY() < prevPoint.getY() &&
                        currentPoint.getX() < afterPoint.getX() &&
                        currentPoint.getY() == afterPoint.getY()
                )
                {
                    bodyImage = bodyUpCornerLeft;
                }
                else if (currentPoint.getX() < prevPoint.getX() &&
                        currentPoint.getY() == prevPoint.getY() &&
                        currentPoint.getX() == afterPoint.getX() &&
                        currentPoint.getY() > afterPoint.getY()
                )
                {
                    bodyImage = bodyDownCornerLeft;
                }
                else if (currentPoint.getX() == prevPoint.getX() &&
                        currentPoint.getY() > prevPoint.getY() &&
                        currentPoint.getX() > afterPoint.getX() &&
                        currentPoint.getY() == afterPoint.getY()
                )
                {
                    bodyImage = bodyDownCornerRight;
                }
            }
            // Select the correct image for the body segment based on the direction of the front of the segment
            if (currentPoint.getX() > prevPoint.getX() && bodyImage == null) {
                bodyImage = bodyRight;
            } else if (currentPoint.getX() < prevPoint.getX() && bodyImage == null) {
                bodyImage = bodyLeft;
            } else if (currentPoint.getY() > prevPoint.getY() && bodyImage == null) {
                bodyImage = bodyDown;
            } else if (currentPoint.getY() < prevPoint.getY() && bodyImage == null) {
                bodyImage = bodyUp;
            }

            gc.drawImage(bodyImage, currentPoint.getX() * SQUARE_SIZE, currentPoint.getY() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
        }
    }


    /**
     * <p>The function makes the snake go right.</p>
     */
    public void moveRight() {
        snakeHead.x++;
    }

    /**
     * <p>The function makes the snake go left.</p>
     */
    public void moveLeft() {
        snakeHead.x--;
    }

    /**
     * <p>The function makes the snake go up.</p>
     */
    public void moveUp() {
        snakeHead.y--;
    }

    /**
     * <p>The function makes the snake go down.</p>
     */
    public void moveDown() {
        snakeHead.y++;
    }


    /**
     * <p>The function is shrink the snake size randomly.</p>
     */
    public void shrinkSnakeSize(){
        Random random = new Random();
        int randomNumber = random.nextInt(snakeBody.size());
        if(randomNumber == 0){
            int size = snakeBody.size();
            for (int i = 0; i < size; i++) {
                snakeBody.add(new Point(-1,-1));
            }
        }else{
            if(randomNumber > snakeBody.size()/2){
                randomNumber = snakeBody.size()/2;
            }
            for (int i = 0; i < randomNumber; i++) {
                snakeBody.remove(snakeBody.size() - 1);
            }
        }

    }
}
