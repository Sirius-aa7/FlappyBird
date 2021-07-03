package com.example.flappybird2;

import java.util.Random;

public class Tube {

    private int tubeX, topTubeOffsetY;// var 2 is top tube bottom edge coordinate.
    private Random random;
    private int tubeColor;
  //  private int tubeGap; newly added

    public Tube(int tubeX, int topTubeOffsetY) {
        this.tubeX = tubeX;
        this.topTubeOffsetY = topTubeOffsetY;
        random= new Random();
    }

    public void setTubeColor(){
        tubeColor= random.nextInt(2);
    }

    public int getTubeColor(){
        return tubeColor;
    }

    public int getTubeX() {
        return tubeX;
    }

    public int getTopTubeOffsetY() {
        return topTubeOffsetY;
    }

    public int getTopTubeY(){
        return topTubeOffsetY- AppConstants.getBitmapBank().getTubeHeight();
    }

    public int getBottomTubeY(){
        return topTubeOffsetY+AppConstants.gapBetweenTopBotTubes;//600
    }

    public void setTubeX(int tubeX) {
        this.tubeX = tubeX;
    }

    public void setTopTubeOffsetY(int topTubeOffsetY) {
        this.topTubeOffsetY = topTubeOffsetY;
    }

    //    public void setTubeGap(){tubeGap= random.nextInt(2);}//new addition

    //  public int getTubeGap(){return tubeGap;}//new addition


}
