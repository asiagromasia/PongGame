package com.example.pong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;

import java.util.Timer;
import java.util.TimerTask;

public class LevelOne extends AppCompatActivity {

    GameView gameView;
    Timer timer;
    TimerTask timerTask;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        gameView = new GameView (this, size.x, size.y);
        setContentView(gameView);

    }

    @Override
    protected void onResume(){
        super.onResume();

        //start the timer
        startTimer();

        gameView.resume();
    }

    private void startTimer() {
        //instanciate  a new timer
        timer = new Timer();
        initializeTimerTask();

        //schedule the timer
        //1000 milliseconds = 1 sec, so I set this to 60 seconds we can change this..
        timer.schedule(timerTask, 60000);
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run () {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LevelOne.this);

                        builder.setCancelable(false);
                        builder.setTitle("Time is up!");
                        builder.setMessage(" Game Over!");
                        //Exit button, which exits app
                        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                System.exit(0);
                            }
                        });
                        //try again opens PlayerName Activity
                        builder.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                Intent intent = new Intent("com.example.pong.PlayerName");
                                startActivity(intent);
                            }
                        });

                        builder.create().show();
                    }
                });
            }

        }

        ;
    }


    @Override
    protected void onPause(){
        super.onPause();

        gameView.pause();
    }
}
