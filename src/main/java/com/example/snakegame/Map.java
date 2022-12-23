package com.example.snakegame;

public class Map {
    private int index;
    private int SQUARE_SIZE = 25;
    private Barrier bar1 = new Barrier(SQUARE_SIZE*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE*5,SQUARE_SIZE);
    private Barrier bar2 = new Barrier(SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE*5,SQUARE_SIZE);
    private Barrier[] bar3 = {
            new Barrier(SQUARE_SIZE*11,SQUARE_SIZE*8,SQUARE_SIZE*11,SQUARE_SIZE),
            new Barrier(SQUARE_SIZE*11,SQUARE_SIZE*9,SQUARE_SIZE,SQUARE_SIZE*6),
            new Barrier(SQUARE_SIZE*21,SQUARE_SIZE*9,SQUARE_SIZE,SQUARE_SIZE*6),
            new Barrier(SQUARE_SIZE*11,SQUARE_SIZE*15,SQUARE_SIZE*11,SQUARE_SIZE)
    };
    private Barrier Maps[][] = {
            {bar1,bar2},
            {bar1},
            {bar2},
            //{bar3}
    };
    //Barrier barrier = new Barrier(SQUARE_SIZE*SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE*5,SQUARE_SIZE);
    //Barrier barrier2 = new Barrier(SQUARE_SIZE,SQUARE_SIZE,SQUARE_SIZE*5,SQUARE_SIZE);
    Map(){
        this.index = 0;
    }

    public int getIndex(){
        return this.index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    public int getMapSize(){
        return Maps.length;
    }

    public Barrier[] getMap(){
        return this.Maps[this.index];
    }

}
