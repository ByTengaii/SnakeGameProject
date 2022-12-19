package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Barrier {
    private int x;
    private int y;
    private int WIDTH;
    private int HEIGHT;

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

    public void barrierRender(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.fillRect(this.x ,this.y,this.WIDTH,this.HEIGHT);
    }
}
