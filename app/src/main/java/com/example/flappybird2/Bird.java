package com.example.flappybird2;

public class Bird {

    private int birdX, birdY, currentFrame,velocity;
    public static int maxFrame;

    public Bird() {
        birdX=AppConstants.SCREEN_WIDTH/2-AppConstants.getBitmapBank().getBirdWidth()/2;
        birdY=AppConstants.SCREEN_HEIGHT/2-AppConstants.getBitmapBank().getBirdHeight()/2;
        currentFrame=0;
        maxFrame=1;
        velocity=0;
    }

    //getter for velocity

    public int getVelocity() {
        return velocity;
    }

    //setter for velocity

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }


    //getter for current frame

    public int getCurrentFrame() {
        return currentFrame;
    }

    //setter for current frame

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    //getter for x coord of bird
    public int getX(){
        return birdX;
    }

    //getter for y coord of bird
    public int getY(){
        return birdY;
    }

    //setter for x coord

    public void setX(int birdX) {
        this.birdX = birdX;
    }

    //setter for y coord

    public void setY(int birdY) {
        this.birdY = birdY;
    }
}
