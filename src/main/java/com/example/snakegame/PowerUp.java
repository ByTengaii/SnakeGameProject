package com.example.snakegame;

import java.util.Random;

public class PowerUp {
    public enum powerType{  CHANGE_SNAKE_SIZE, WALLS_DEACTIVATED, BONUS_POINT, CLASSIC}
    private powerType type;


    PowerUp(){
        this.setType();
    }

    public powerType getType(){
        return this.type;
    }

    public void setType(){
        /*
        *
        * Random random = new Random();
        * Object[] objs = powerType.values();
        * this.type = (powerType)objs[random.nextInt(objs.length)];
        */
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        if(randomNumber <= 50){
            this.type = powerType.CLASSIC;
        } else if (randomNumber <= 75) {
            this.type = powerType.BONUS_POINT;
        } else if (randomNumber <= 85) {
            this.type = powerType.WALLS_DEACTIVATED;
        } else if (randomNumber <= 100) {
            this.type = powerType.CHANGE_SNAKE_SIZE;
        }
    }
}
