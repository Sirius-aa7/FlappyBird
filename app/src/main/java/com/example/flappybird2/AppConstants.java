package com.example.flappybird2;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static BitmapBank bitmapBank;//object reference
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static int gapBetweenTopBotTubes;
    static int numberOfTubes;
    static int tubeVelocity;
    static int minTubeOffsetY;
    static int maxTubeOffsetY;
    static int distBetTubes;
    static Context gameActivityContext;



    public static void initialization(Context context){
        setScreenSize(context);
        bitmapBank= new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine= new GameEngine();
    }

    public static void setGameConstants() {
        AppConstants.gravity=3;
        AppConstants.VELOCITY_WHEN_JUMPED= -40;
        gapBetweenTopBotTubes=600;
        AppConstants.numberOfTubes=2;
        AppConstants.tubeVelocity=12;
        AppConstants.minTubeOffsetY = (int) (AppConstants.gapBetweenTopBotTubes/2.0);
        AppConstants.maxTubeOffsetY = AppConstants.SCREEN_HEIGHT-AppConstants.minTubeOffsetY-AppConstants.gapBetweenTopBotTubes;
        AppConstants.distBetTubes=AppConstants.SCREEN_WIDTH*3/4;
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
