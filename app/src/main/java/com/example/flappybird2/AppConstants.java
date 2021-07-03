package com.example.flappybird2;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.Random;

public class AppConstants {

    static BitmapBank bitmapBank;//object reference
    static GameEngine gameEngine;
    //Random random;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY;
    static int maxTubeOffsetY;
    static int distBetTubes;
   static Context gameActivityContext;

    static int gapBetweenTopBotTubes=600;//=600 for changing on the spot
    static int gapINT;
    static SoundBank soundBank;

    public static void initialization (Context context) {// done in main activity
        setScreenSize(context);
        bitmapBank= new BitmapBank(context.getResources());
        //gettingGap();
        setGameConstants();
        gameEngine= new GameEngine();
        soundBank= new SoundBank(context);
    }

    public static SoundBank getSoundBank(){
        return soundBank;
    }

    public static void gettingGap() {
        //gapINT = 0;//for changing on the spot this or assigning 600 to the above var
        gapINT = new Random().nextInt(3);

        if (gapINT == 0) {
            gapBetweenTopBotTubes = 600;
        } else if(gapINT==1) {
            gapBetweenTopBotTubes = 800;
        } else{
            gapBetweenTopBotTubes=550;
        }
    }

    public static void setGameConstants() {
        AppConstants.gravity=3;
        AppConstants.VELOCITY_WHEN_JUMPED= -40;
        AppConstants.numberOfTubes = 2;
        AppConstants.tubeVelocity = 12;
        //gettingGap();
        AppConstants.minTubeOffsetY = (int) (gapBetweenTopBotTubes / 2.0);//300
        AppConstants.maxTubeOffsetY = AppConstants.SCREEN_HEIGHT - AppConstants.minTubeOffsetY - AppConstants.gapBetweenTopBotTubes;//600
        AppConstants.distBetTubes = AppConstants.SCREEN_WIDTH * 3 / 4;
    }

    //return BitmapBank instance
    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    //return GameEngine instance
    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics= new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        AppConstants.SCREEN_WIDTH = width;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_HEIGHT= height;
    }
}
