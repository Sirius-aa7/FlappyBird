package com.example.flappybird2;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread{

    SurfaceHolder surfaceHolder;// surfaceholder object reference
    boolean isRunning;//Flag to detect if its running or not
    long startTime, loopTime;
    long DELAY=33;// delay betn screen refreshes

    public GameThread(SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        isRunning=true;
    }

    @Override
    public void run() {
        // looping unitl boolean is false
        while (isRunning){
            startTime= SystemClock.uptimeMillis();
            //locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas!=null){
                synchronized (surfaceHolder){
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawBird(canvas);
                    AppConstants.getGameEngine().updateAndDrawTubes(canvas);
                    //unlocking the canvas
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //loop time
            loopTime = SystemClock.uptimeMillis()-startTime;
            //pausing to make sure v update for the right amount per second
            if (loopTime< DELAY){
                try{
                    Thread.sleep(DELAY-loopTime);
                }catch (InterruptedException e){
                    Log.e("Interrupted","Interrupted while sleeping");
                }
            }
        }
    }

    //return whether thread is running
    public boolean isRunning(){
        return isRunning;
    }

    //sets the thread state , false=stopped, true = running
    public void setIsRunning(boolean state){
         isRunning= state;
    }
}
