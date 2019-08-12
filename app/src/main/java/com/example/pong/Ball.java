package com.example.pong;

import android.graphics.RectF;

import java.util.Random;

public class Ball {
    private RectF mRect; //RectF object holds 4 float values which define the four coordinates of a rectangle
    private float xVelocity;
    private float yVelocity;
    private float ballWidth;
    private float ballHeight;


    public Ball(int screenX, int screenY) {

        //the ball is relative to the screen resolution
        ballWidth = screenX/50;
        ballHeight = ballWidth;

        //the ball starts by traveling up from the screen height per second
        yVelocity = screenY / 4;
        xVelocity = yVelocity;

        //initializes the Rect that represents the ball
        mRect = new RectF();

    }

    public RectF getRect(){
        return mRect;
    }

    //This update method is called once every frame, it updates and changes the position the top and LEFT values of the ball
    //depending on on the velocity -> xVelocity & yVelocity divided by the number of frames per second
    public void update(long fps) {
        mRect.left = mRect.left + (xVelocity / fps);
        mRect.top = mRect.top + (yVelocity / fps);
        mRect.right = mRect.left + ballWidth;
        mRect.bottom = mRect.top - ballHeight;
    }

    //Reverses the vertical direction
    public void reverseYVelocity(){
        yVelocity = -yVelocity;
    }


    //Reverses the horizontal direction
    public void reverseXVelocity(){
        xVelocity = -xVelocity;
    }

    //This method generates a random number either 0 or 1
    public void setRandomVelocity() {
        Random generator = new Random();
        int number = generator.nextInt(2);

        if (number == 0 ){
            reverseXVelocity();
        }
    }


    //speeds up the ball
    //Increase by 10 or decrease to make harder
    public void increaseVelocity(){
        xVelocity = xVelocity + xVelocity / 10;
        yVelocity = yVelocity + yVelocity / 10;
    }


    public void clearObstacleY (float y) {
        mRect.bottom = y;
        mRect.top = y - ballHeight;
    }

    public void clearObstacleX (float x) {
        mRect.left = x;
        mRect.right = x + ballWidth;

    }

    public void reset (int x, int y) {
        mRect.left = x / 2;
        mRect.top = y - 20;
        mRect.right = x / 2 + ballWidth;
        mRect.bottom = y - 20 - ballHeight;
    }


}
