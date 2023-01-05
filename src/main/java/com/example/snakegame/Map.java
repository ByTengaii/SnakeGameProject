package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.*;

public class Map {
    /*public enum wallStation {ACTIVATED, DEACTIVATED};
    private wallStation station;
    private File[] gameMapFiles;
    private String PROJECT_PATH = System.getProperty("user.dir");
    private String MAPS_PATH = "\\src\\main\\java\\com\\example\\snakegame\\maps";
    private char WALL_CODE = 'x';
    private int level;
    private int SQUARE_SIZE = 25;
    private Barrier Maps[][][];

    Map(){
        station = wallStation.ACTIVATED;
        gameMapFiles = new File(PROJECT_PATH+MAPS_PATH).listFiles();
        Maps = new Barrier[gameMapFiles.length][24][33];
        this.level = 1;
        emptyMap();
        getterMap();
    }

    public wallStation getWallStation(){
        return this.station;
    }

    public void setWallStation(wallStation station){
        this.station = station;
    }

    public int getLevel(){ return this.level; }

    public void setLevel(int level) { this.level = level; }

    public void emptyMap(){
        for (int x = 0; x < Maps[this.level].length; x++) {
            for (int y = 0; y < Maps[this.level][x].length; y++) {
                Maps[this.level][x][y] = new Barrier(y*SQUARE_SIZE, x*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, Barrier.barrierStation.NON);
            }
        }
    }

    public void getterMap(){
        File file = gameMapFiles[this.level];
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            int x = 0;
            while((st = br.readLine()) != null){
                //System.out.println(st);
                for (int y = 0; y < st.length(); y++) {
                    if(st.charAt(y) == WALL_CODE){
                        Maps[this.level][x][y].setStation(Barrier.barrierStation.ACTIVATED);
                        System.out.println("X : "+x+" Y: "+y);
                    }
                }
                x++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void renderAllBarriers(GraphicsContext gc){
        for (int x = 0; x < Maps[this.level].length; x++) {
            for (Barrier barrier : Maps[this.level][x]){
                barrier.barrierRender(gc);
            }
        }
        if(getWallStation() == wallStation.DEACTIVATED){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Digital-7", 15));
            gc.fillText("Walls: " + this.station.toString(), 10, 15);
        }
    }

    public void changeMap(Score score){
        if (score.getScore() >= 10 && getLevel()+1 < gameMapFiles.length){
            setLevel(getLevel() + 1);
            score.setScore(-score.getScore());
        }
    }

    public boolean isThereBarrier(Snake snake){
        if(getWallStation() == wallStation.ACTIVATED){
            for (int x = 0; x < Maps[this.level].length; x++) {
                for (Barrier barrier : Maps[this.level][x]){
                    if(barrier.getStation() == Barrier.barrierStation.ACTIVATED){
                        if (
                                snake.snakeHead.x*SQUARE_SIZE >= barrier.getX() &&
                                        snake.snakeHead.x*SQUARE_SIZE < (barrier.getWIDTH())&&
                                        snake.snakeHead.y*SQUARE_SIZE >= barrier.getY() &&
                                        snake.snakeHead.y*SQUARE_SIZE < (barrier.getHEIGHT())
                        ){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isThereBarrier( int foodX, int foodY){
        for (int x = 0; x < Maps[this.level].length; x++) {
            for (Barrier barrier : Maps[this.level][x]){
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
        }

        if (this.level + 1 != Maps.length){
            for (int x = 0; x < Maps[this.level].length; x++) {
                for (Barrier barrier : Maps[this.level][x]){
                    if
                    (
                            foodX*SQUARE_SIZE >= barrier.getX() &&
                                    foodX*SQUARE_SIZE < (barrier.getWIDTH())&&
                                    foodY*SQUARE_SIZE >= barrier.getY() &&
                                    foodY*SQUARE_SIZE < (barrier.getHEIGHT())
                    )
                    {
                        //return true;
                    }
                }
            }
        }

        return false;
    }*/
    public enum wallStation {ACTIVATED, DEACTIVATED};
    private wallStation station = wallStation.DEACTIVATED;
    private File[] gameMapFiles;
    String projectPath = System.getProperty("user.dir");
    String mapsPath = "\\src\\main\\java\\com\\example\\snakegame\\maps";
    private char WALL_CODE = 'x';
    private int level;
    private int SQUARE_SIZE = 25;

    //UP
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

    public void changeMap(Score score){
        if (score.getScore() >= 100 && getLevel()+1 < getMapSize()){
            setLevel(getLevel() + 1);
            score.setScore(-score.getScore());
        }
    }

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

}
