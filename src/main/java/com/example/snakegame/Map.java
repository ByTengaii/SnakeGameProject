package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;

public class Map {

    public enum wallStation {ACTIVATED, DEACTIVATED};
    private wallStation station = wallStation.DEACTIVATED;
    private File[] gameMapFiles;
    String projectPath = System.getProperty("user.dir");
    String mapsPath = "\\src\\main\\java\\com\\example\\snakegame\\maps";
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


    public wallStation getWallStation(){
        return this.station;
    }

    public void setWallStation(wallStation station){
        this.station = station;
    }

    public int getLevel(){ return this.level; }

    public void setLevel(int level) { this.level = level; }


    public int getMapSize(){
        return Maps.length;
    }

    public Barrier[] getMap(){
        return this.Maps[this.level];
    }
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
        if(getWallStation() == wallStation.ACTIVATED){
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
            barrier.barrierRender(gc);
        }
        if(getWallStation() == wallStation.DEACTIVATED){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Digital-7", 15));
            gc.fillText("Walls: " + this.station.toString(), 10, 15);
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
