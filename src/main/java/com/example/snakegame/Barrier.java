package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;

public class Barrier {
    String projectPath = System.getProperty("user.dir");
    String imgPath = "\\src\\main\\resources\\com\\example\\snakegame\\img\\";
    private int x;
    private int y;
    private int WIDTH;
    private int HEIGHT;

    /**
     * <p>Create a barrier object with coordinates of up-left corner.</p>
     * @param x x coordinate of barrier. If you want to take coordinate like square, you need to duplicate it with Square Size
     * @param y y coordinate of barrier. If you want to take coordinate like square, you need to duplicate it with Square Size
     * @param WIDTH width of barrier. If you want to give width like "width is 5 square", you need to duplicate it with Square Size
     * @param HEIGHT height of barrier. If you want to give height like "height is 5 square", you need to duplicate it with Square Size
     */
    Barrier (int x , int y, int WIDTH,int HEIGHT )
    {
        this.x = x;
        this.y = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    /**
     * <p>The function is a getter method for barrier's x coordinate.</p>
     * @return the x value.
     */
    public int getX(){
        return this.x;
    }

    /**
     * <p>The function is a getter method for barrier's y coordinate.</p>
     * @return the y value.
     */
    public int getY(){
        return this.y;
    }

    /**
     * <p>The function is a getter method for barrier's height.</p>
     * @return the sum of height and y values.
     */
    public int getHEIGHT(){
        return this.y + this.HEIGHT;
    }

    /**
     * <p>The function is a getter method for barrier's width.</p>
     * @return the sum of x and width values.
     */
    public int getWIDTH(){
        return this.x + this.WIDTH;
    }

    /**
     * <p>Render the barrier on the map </p>
     * @param gc graphic content
     */
    public void barrierRender(GraphicsContext gc,Boolean wall)
    {
        Image barrierImage = new Image(projectPath+imgPath+"WALL_ACTIVE.png");
        Image nonBarrierImage = new Image(projectPath+imgPath+"WALL_NOTACTIVE.png");
        if(wall){
            gc.drawImage(barrierImage,this.x,this.y,this.WIDTH,this.HEIGHT);
        }else {
            gc.drawImage(nonBarrierImage,this.x,this.y,this.WIDTH,this.HEIGHT);
        }
    }
}
