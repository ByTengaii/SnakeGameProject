package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;

public class Map {

    public enum wallStation {ACTIVATED, DEACTIVATED};
    private wallStation situation = wallStation.DEACTIVATED;
    String projectPath = System.getProperty("user.dir");
    String imgPath = "\\src\\main\\resources\\com\\example\\snakegame\\img\\";
    private char WALL_CODE = 'x';
    private int level;
    private int SQUARE_SIZE = 25;

    //Barriers
    private Barrier upSide = new Barrier(0,0,SQUARE_SIZE*33,SQUARE_SIZE);
    private Barrier leftSide = new Barrier(0,0,SQUARE_SIZE,SQUARE_SIZE*24);
    private Barrier downSide = new Barrier(0,SQUARE_SIZE*23,SQUARE_SIZE*33,SQUARE_SIZE);
    private Barrier rightSide = new Barrier(SQUARE_SIZE*32,0,SQUARE_SIZE,SQUARE_SIZE*24);
    private Barrier centerUp = new Barrier(SQUARE_SIZE*10,SQUARE_SIZE*6,SQUARE_SIZE*12,SQUARE_SIZE);
    private Barrier centerDown = new Barrier(SQUARE_SIZE*10,SQUARE_SIZE*17,SQUARE_SIZE*12,SQUARE_SIZE);



    private Barrier Maps[][] = {
            {upSide,rightSide,leftSide,downSide},
            {upSide,rightSide,leftSide,downSide,centerUp,centerDown},
            {}
    };

    Map(){
        this.level = 0;
    }

    /**
     * <p>The function is a getter method.</p>
     * @return the wall station.
     */
    public wallStation getWallSituation(){
        return this.situation;
    }

    /**
     * <p>The function is a setter method.</p>
     * @param situation The situation which is will set the current.
     */
    public void setWallSituation(wallStation situation){
        this.situation = situation;
    }

    /**
     * <p>The function is a getter method for level.</p>
     * @return the map level.
     */
    public int getLevel(){ return this.level; }

    /**
     * <p>The function is set the current level of map.</p>
     * @param level The level which is will set the current level.
     */
    public void setLevel(int level) { this.level = level; }

    /**
     * <p>The function is a getter method for map size.</p>
     * @return the Maps array's length.
     */
    public int getMapSize(){
        return Maps.length;
    }

    /**
     * <p>
     *     The function is a getter method for map. There are two different function for this.
     *     This is one of them and the function is directly getter the current map.
     * </p>
     * @return the current map in the Maps array.
     */
    public Barrier[] getMap(){
        return this.Maps[this.level];
    }

    /**
     * <p>This is the other getter map function. The function is getter the one of next maps.</p>
     * @param level Which level we want to get.
     * @return the map which we want to get.
     */
    public Barrier[] getMap(int level){
        if(this.level != Maps.length-1){
            return this.Maps[this.level+level];
        }else{
            return this.Maps[this.level];
        }

    }

    /**
     * <p>true or false value. If snake touch the barrier return true, If it don't, return false.</p>
     * @param snake
     * @return true or false value. If snake touch the barrier return true, If it don't, return false.
     */
    public boolean isThereBarrier(Snake snake){
        if(getWallSituation() == wallStation.ACTIVATED){
            for (Barrier barrier : Maps[this.level]) {
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
        }else{
            return false;
        }
    }

    /**
     * <p>This method check the barrier for food spawn. If the location is part of any barrier. The food is not spawn. </p>
     * @param foodX X cordinate of food
     * @param foodY Y coordinate of food
     * @return
     */
    public boolean isThereBarrier( int foodX, int foodY){
        for (Barrier barrier : getMap()) {
            if
            (
                    foodX*SQUARE_SIZE >= barrier.getX() &&
                            foodX*SQUARE_SIZE < (barrier.getWIDTH())&&
                            foodY*SQUARE_SIZE >= barrier.getY() &&
                            foodY*SQUARE_SIZE < (barrier.getHEIGHT())
            )
            {
                return true;
            }
        }
        for(Barrier barrier : getMap(1)) {
            if
            (
                    foodX*SQUARE_SIZE >= barrier.getX() &&
                            foodX*SQUARE_SIZE < (barrier.getWIDTH())&&
                            foodY*SQUARE_SIZE >= barrier.getY() &&
                            foodY*SQUARE_SIZE < (barrier.getHEIGHT())
            )
            {
                return true;
            }
        }
        return false;
    }


    /**
     * <p>Render the all barrier object that included in map object.</p>
     * @param gc GraphicContent
     */

    public void renderAllBarriers(GraphicsContext gc){
        for (Barrier barrier : Maps[this.level]){
            if(getWallSituation() == wallStation.ACTIVATED){
                barrier.barrierRender(gc,true);
            }else{
                barrier.barrierRender(gc,false);
            }

        }
        if(getWallSituation() == wallStation.DEACTIVATED){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Digital-7", 15));
            gc.fillText("                                   "+"Walls: " + this.situation.toString(), 10, 15);
        }
    }

    /**
     * <p>This method change the level design if score reach the 100.</p>
     * @param score score
     */
    public void changeMap(Score score){
        if (score.getScore() >= 100 && getLevel()+1 < getMapSize()){
            setLevel(getLevel() + 1);
            score.setScore(-score.getScore());
        }
    }



}
