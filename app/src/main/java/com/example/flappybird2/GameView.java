package com.example.flappybird2;

import android.content.Context;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import  android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    GameThread gameThread;


    public GameView(Context context) {
        super(context);
        initView();

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (!gameThread.isRunning()){
            gameThread= new GameThread(holder);
            gameThread.start();
        }else {
            gameThread.start();
        }

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry){
                try {
                    gameThread.join();
                    retry = false;
                }catch (InterruptedException e){}
            }
        }
    }

    void initView(){
        SurfaceHolder holder =  getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action= event.getAction();
        // tap is detected
        if (action== MotionEvent.ACTION_DOWN){
            if (GameEngine.gameState ==0){
                GameEngine.gameState = 1;
                AppConstants.getSoundBank().playSwoosh();
            }else {
                AppConstants.getSoundBank().playWing();
            }
            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
        }
        return true;
    }
}
