package com.example.flappybird2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.flappybird2.AppConstants;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

//import static com.example.flappybird2.AppConstants.gapBetweenTopBotTubes;



public class GameEngine {

    BackgroundImage backgroundImage;
    Bird bird;
    GameView gameView;
    static int gameState;
    ArrayList<Tube> tubes;
    Random random;
    int score;
    int scoringTube;
    Paint scorePaint;
    AppConstants appConstants;

    public GameEngine() {//extends AppConstants()
        backgroundImage = new BackgroundImage();
        bird = new Bird();
        // 0 = Not Started
        // 1 = Playing
        // 2 = GameOver
        gameState = 0;
        tubes = new ArrayList<>();
        random = new Random();
        for (int i = 0; i < AppConstants.numberOfTubes; i++) {
            int tubeX = AppConstants.SCREEN_WIDTH + i * AppConstants.distBetTubes;

            int topTubeOffsetY = AppConstants.minTubeOffsetY + random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);

            Tube tube = new Tube(tubeX, topTubeOffsetY);
            tubes.add(tube);
        }
        score = 0;
        scoringTube = 0;
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(100);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    public void updateAndDrawTubes(Canvas canvas) throws InterruptedException {
        if (gameState == 1) {
            if ((tubes.get(scoringTube).getTubeX() < bird.getX() + AppConstants.getBitmapBank().getBirdWidth())
                    && (tubes.get(scoringTube).getTopTubeOffsetY() > bird.getY()
                    || tubes.get(scoringTube).getBottomTubeY() < (bird.getY() + AppConstants.getBitmapBank().getBirdHeight()))) {
                //gameover screen
                gameState = 2;
                //Log.d("Game", "Over");
                Context context = AppConstants.gameActivityContext;
                Intent intent = new Intent(context,GameOver.class);
                intent.putExtra("score",score);
                context.startActivity(intent);
                ((Activity) context).finish();
                AppConstants.getSoundBank().playHit();
                Thread.sleep(700);// adds a delay of 0.7 seconds
                AppConstants.getSoundBank().playDie();
            } else if (tubes.get(scoringTube).getTubeX() < bird.getX() - AppConstants.getBitmapBank().getTubeWidth()) {
                score++;
                scoringTube++;
                if (scoringTube > AppConstants.numberOfTubes - 1) {
                    scoringTube = 0;
                }
                AppConstants.getSoundBank().playPoint();
            }
            for (int i = 0; i < AppConstants.numberOfTubes; i++) {
                if (tubes.get(i).getTubeX() < -AppConstants.getBitmapBank().getTubeWidth()) {
                    tubes.get(i).setTubeX(tubes.get(i).getTubeX() + AppConstants.numberOfTubes * AppConstants.distBetTubes);
                    AppConstants.gettingGap();
                    int topTubeOffsetY = AppConstants.minTubeOffsetY +
                            random.nextInt(AppConstants.maxTubeOffsetY - AppConstants.minTubeOffsetY + 1);
                    tubes.get(i).setTopTubeOffsetY(topTubeOffsetY);
                    tubes.get(i).setTubeColor();
                   // tubes.get(i).setTubeGap();
                }
                tubes.get(i).setTubeX(tubes.get(i).getTubeX() - AppConstants.tubeVelocity);
                //AppConstants.gettingGap();de comment this if want changing wrt time
                if (tubes.get(i).getTubeColor() == 0) {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                } else {
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeTop(), tubes.get(i).getTubeX(), tubes.get(i).getTopTubeY(), null);
                    canvas.drawBitmap(AppConstants.getBitmapBank().getRedTubeBottom(), tubes.get(i).getTubeX(), tubes.get(i).getBottomTubeY(), null);
                }
                //appConstants.getGapBetweenTopBotTubes(tubes);
                //if (tubes.get(i).getTubeGap() == 0) {

                 //   gapBetweenTopBotTubes=600;
                    //gapBetweenTopBotTubes = 600;
                    // tube.setTubeGap();
                }     // else {
                    //appConstants.getGapBetweenTopBotTubes();
                    //    gapBetweenTopBotTubes = 150;
                   //}

                   //if (tubes.get(i).getTubeGap() == 0){ }
            canvas.drawText("PT: " + score, 0, 110, scorePaint);
            }


    }

    public void updateAndDrawBackgroundImage(Canvas canvas) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getCVelocity());
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX() +
                    AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);

        }
    }

    public void updateAndDrawBird(Canvas canvas) {
        if (gameState == 1) {
            if (bird.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getBirdHeight()) || bird.getVelocity() < 0) {
                bird.setVelocity(bird.getVelocity() + AppConstants.gravity);
                bird.setY(bird.getY() + bird.getVelocity());
            }

        }
        int currentFrame = bird.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getBird(currentFrame), bird.getX(), bird.getY(), null);
        currentFrame++;
        //if it exceeds maxframe, reinitialise to 0
        if (currentFrame > bird.maxFrame) {
            currentFrame = 0;
        }
        bird.setCurrentFrame(currentFrame);
    }
}
