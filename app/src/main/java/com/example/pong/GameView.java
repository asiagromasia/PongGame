package com.example.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    Thread gameThread = null;
    SurfaceHolder pongHolder;

    volatile boolean gameRunning;

    //game is paused at the start of the game
    boolean gamePaused = true;

    Canvas gameCanvas;
    Paint gamePaint;

    long gameFPS;

    int sizeScreenX;
    int sizeScreenY;

    Bar playerBar;

    Ball playerBall;


    //score
    int playerScore = 0;

    int playerLives = 3;


    public GameView (Context context, int x, int y) {

        super(context);

        sizeScreenX = x;
        sizeScreenY = y;

        pongHolder = getHolder();
        gamePaint = new Paint();

        playerBar = new Bar(sizeScreenX, sizeScreenY);
        playerBall = new Ball(sizeScreenX, sizeScreenY);


        setupAndRestart();
    }


    public void setupAndRestart() {
        playerBall.reset(sizeScreenX, sizeScreenY);

        if (playerLives == 0 ) {
            playerScore = 0;
            playerLives = 3;
        }

    }

    @Override
    public void run(){
        while (gameRunning) {
            long startFrameTime = System.currentTimeMillis();
            if(gamePaused){
                update();
            }

            draw();

            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame >= 1) {
                gameFPS = 1000 /timeThisFrame;
            }
        }
    }

    public void update() {
        playerBar.update(gameFPS);
        playerBall.update(gameFPS);

        if (RectF.intersects(playerBar.getRect(), playerBall.getRect())) {
            playerBall.setRandomVelocity();
            playerBall.reverseYVelocity();
            playerBall.clearObstacleY(playerBar.getRect().top - 2);

            playerScore++;
            playerBall.increaseVelocity();
        }

        if (playerBall.getRect().bottom > sizeScreenY) {
            playerBall.reverseYVelocity();
            playerBall.clearObstacleY(sizeScreenY - 2);

            //lose a life
            playerLives--;

            //loses life if...
            if (playerLives == 0 ) {
                gamePaused = true;
                playerLives = 3;

            }
        }


        //boundaries and reverses ball back to the the vertical or horizontal axis
        if (playerBall.getRect().top < 0) {
            playerBall.reverseYVelocity();
            playerBall.clearObstacleY(12);

        }

        if (playerBall.getRect().left < 0) {
            playerBall.reverseXVelocity();
            playerBall.clearObstacleX(2);
        }

        if (playerBall.getRect().right > sizeScreenX) {
            playerBall.reverseXVelocity();
            playerBall.clearObstacleX(sizeScreenX - 22);
        }
    }
//this method draws the objects
    public void draw() {
        if (pongHolder.getSurface().isValid()) {
            gameCanvas = pongHolder.lockCanvas();

            //screen
            gameCanvas.drawColor(Color.argb(250, 4, 6, 1 ));//black background

            gamePaint.setColor(Color.argb(250,255,255, 255));// white ball

            //draws bar
            gameCanvas.drawRect(playerBar.getRect(), gamePaint);

            //draws ball
            gameCanvas.drawRect(playerBall.getRect(), gamePaint);

            //
            gamePaint.setColor(Color.argb(250,255, 255, 255)); //white text letters

            //remove.....later for scoring temporary
            gamePaint.setTextSize(80);


            gameCanvas.drawText("Score: " + playerScore + " Timer: Currently Not Working", 10, 80, gamePaint);

            //draw everything to the screen
            pongHolder.unlockCanvasAndPost(gameCanvas);

        }

    }

    public void pause() {
        gameRunning = false;
        try{
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error: ", "joining thread");
        }
    }

    public void resume() {
        gameRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                gamePaused = true;


                if (motionEvent.getX() > sizeScreenX/2) {
                    playerBar.setMovementState(playerBar.RIGHT);
                }   else {
                    playerBar.setMovementState(playerBar.LEFT);
                }

                break;

            //player is no longer touching the screen

            case MotionEvent.ACTION_UP:
                playerBar.setMovementState(playerBar.STOPPED);
                break;
        }

        return true;
    }


}


