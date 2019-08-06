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
    boolean gameStopped = false;
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
        if (playerLives==0 ){
            playerScore = 0;
            playerLives = 3;

       /* if ((playerLives==0 || playerScore == 0)) {
            playerLives = 3;
        } else if (playerLives==0){
            stop();
        }
        */
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
            //I added it here and it works partially- it stops but is not starting dialog fragment
            if(playerScore == 3){
                stop();
                gameStopped = true;
                }

               // openGameOver();

            //if (gameStopped){
              //  System.out.print("hi");
            //}
            // following if assigned any of the amount of lives:0,1,2,3 is stopping the game right in the beginning due to the logic(game starts with these lifes and is automatically stopped)
            if (playerLives==4){
                stop();
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
            playerBall.reverseYVelocity( );
            playerBall.clearObstacleY(sizeScreenY - 2);

            //lose a life
            playerLives--;

            //loses life if...
              if (playerLives == 0) {

                  // gamePaused = true;
                  //   Intent intent = new Intent(this, EditNameDialogFragment.class));
                  //   startActivity(intent);
                  // stop();
                  // startActivity(new Intent(this,EditNameDialogFragment.class));
                  //   openGameOver();
                  setupAndRestart( );

              }


        }
        /*if ((playerLives==0 || playerScore == 0)) {

            playerLives = 3;
        } else if (playerLives==0){
            stop();
        }*/


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


            gameCanvas.drawText("Score: " + playerScore + " Lives: " + + playerLives, 10, 80, gamePaint);

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
    public void stop(){
        gameRunning = false;
        gameStopped = true;

}
        //   gameThread.stop(); shows deprecated error
      //  openGameOver();
//        try {
//            gameThread.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
     //   openGameOver();




    /* I believe that you can not create this in here but should be in activity-> levelOne.java
    public void openGameOver() {
      //  FragmentManager fm = getSupportFragmentManager();
        DialogFragment newFragment = new EditNameDialogFragment();
        newFragment.show(getSupportFragmentManager(), "The end");
    }*/



//    public void openDialog(View v) {
//        View view = LayoutInflater.from(GameView.this).inflate(R.layout.activity_game_over,null);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameView.this);
//        alertDialogBuilder.setMessage("Game Over!")
//                    .setView(view)
//                    .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                           // Toast.makeText(GameView.this, "You clicked yes button", Toast.LENGTH_LONG).show();
//                            // restart method
//                        }
//                    })
//                    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            //safe score
//                        }
//                    });
//                     AlertDialog alertDialog = alertDialogBuilder.create();
//                        alertDialog.show();
//
//    }
//
//        alertDialogBuilder.setNegativeButton("No, exit", new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface dialog, int which) {
//            finish();
//        }
//        });
//
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

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


