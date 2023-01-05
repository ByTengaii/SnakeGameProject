package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Barrier {
    private int x;
    private int y;
    private int WIDTH;
    private int HEIGHT;

    /**
     * <p>Create a barrier object with coordinates of up-left corner.</p>
     * @param x x coordinate of barrier. If you want to take cordinate like square, you need to duplicate it with Square Size
     * @param y y coordinate of barrier. If you want to take cordinate like square, you need to duplicate it with Square Size
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

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getHEIGHT(){
        return this.y + this.HEIGHT;
    }

    public int getWIDTH(){
        return this.x + this.WIDTH;
    }

    /**
     * <p>Render the barrier on the map </p>
     * @param gc graphic content
     */
    public void barrierRender(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(this.x ,this.y,this.WIDTH,this.HEIGHT);
    }
}
