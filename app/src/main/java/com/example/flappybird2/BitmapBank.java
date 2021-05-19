package com.example.flappybird2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap background;
    Bitmap [] bird;
    Bitmap tubeTop, tubeBottom;
    Bitmap redTubeTop, redTubeBottom;

    public BitmapBank(Resources res) {
        background= BitmapFactory.decodeResource(res, R.drawable.background);
        bird =new Bitmap[2];
        bird[0] = BitmapFactory.decodeResource(res, R.drawable.bird1finze);
        bird[1] = BitmapFactory.decodeResource(res, R.drawable.bird2finze);
        tubeTop= BitmapFactory.decodeResource(res, R.drawable.pipe2fi);
        tubeBottom=BitmapFactory.decodeResource(res, R.drawable.pipe1fi);
        redTubeTop= BitmapFactory.decodeResource(res, R.drawable.pipe2fi);
        redTubeBottom=BitmapFactory.decodeResource(res, R.drawable.pipe1fired);
    }

    //return for red tubes

    public Bitmap getRedTubeTop() {
        return redTubeTop;
    }

    public Bitmap getRedTubeBottom() {
        return redTubeBottom;
    }


    //return tupetop bitmap

    public Bitmap getTubeTop() {
        return tubeTop;
    }

    // return tubeBottom bitmap

    public Bitmap getTubeBottom() {
        return tubeBottom;
    }

    // return tube-width
    public int getTubeWidth(){
        return tubeTop.getWidth();
    }

    // return tube-height
    public int getTubeHeight(){
        return tubeTop.getHeight();
    }

    //return bird bitmap of frame

    public  Bitmap getBird(int frame){
        return bird[frame];
    }

    public int getBirdWidth(){
        return bird[0].getWidth();
    }
    public int getBirdHeight(){
        return bird[0].getHeight();
    }

    //Return bgrd bitmap
    public Bitmap getBackground(){
        return background;
    }

    //return bgrd width
    public int getBackgroundWidth(){
        return background.getWidth();
    }

    //return bgrd heigth
    public int getBackgroundHeight(){
        return background.getHeight();
    }

}

