package com.example.snakegame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Score {

    private int score;

    Score(){
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score){
        this.score += score;
    }

    public void drawScore(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Digital-7", 35));
        gc.fillText("Score: " + this.score, 10, 35);
    }
}
