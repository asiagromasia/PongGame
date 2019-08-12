package com.example.pong;

import android.graphics.RectF;

public class Bar {
    private RectF mRect;

    private float barLength;
    private float barHeight;
    private float xCoord;
    private float yCoord;
    private float barSpeed;


    //ways the bar moves
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    private int barMovement = STOPPED;

    private int screenX;
    private int screenY;

    public Bar (int x, int y) {
        screenX = x;
        screenY = y;

        //value divided by screen width/height
        barLength = screenX /3;
        barHeight = screenY/35;

        //starts the bar at the center of screen
        xCoord = screenX / 3 ;
        yCoord =(screenY/2)+(screenY/3);
        //creates the bar with x & y coord and barLength and height
        mRect = new RectF(xCoord, yCoord,xCoord + barLength, yCoord + barHeight);

        //bar covers entire screen (x)
        barSpeed = screenX;


    }

    //returns object RectF object that represents the bars location
    public RectF getRect(){
        return mRect;
    }


    //sets the state of the bar
    public void setMovementState (int state) {
        barMovement = state;
    }

    public void update (long fps) {
        if (barMovement == LEFT) {
            xCoord = xCoord - barSpeed / fps;
        }

        if (barMovement == RIGHT) {
            xCoord = xCoord + barSpeed / fps;
        }

        //
        if (mRect.left < 0 ) {
            xCoord = 0;
        }
        if (mRect.right > screenX) {
            xCoord = screenX - (mRect.right - mRect.left);
        }

        //updates the bar graphics
        mRect.left = xCoord;
        mRect.right = xCoord + barLength;
    }


}
