package com.example.pong;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


public class LevelOne extends AppCompatActivity {

    GameView gameView;
    //private Object GameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager( ).getDefaultDisplay( );

        Point size = new Point( );
        display.getSize(size);

        gameView = new GameView(this, size.x, size.y);
        setContentView(gameView);

       // openGameOver();


       /* if (gameView.gameStopped){
            openGameOver();
        }*/

    }

    @Override
    protected void onResume() {
        //openGameOver();
        super.onResume( );

        gameView.resume( );
    }

    @Override
    protected void onPause() {
       // openGameOver();
        super.onPause( );
        gameView.pause( );


    }




     @Override
    protected void onStop(){
        if (gameView.gameStopped){
            openGameOver();
        }
        openGameOver();
      super.onStop();
      //   openGameOver();
   //   gameView.stop();

        openGameOver();
      }

    public void openGameOver(){
        DialogFragment newFragment = new EditNameDialogFragment();
        newFragment.show(getSupportFragmentManager( ), "Decide:");
    }
/* this one works as well!
public void openGameOver() {
    FragmentManager fm = getSupportFragmentManager( );
    EditNameDialogFragment alertDialog = EditNameDialogFragment.newInstance("Koniec");
    alertDialog.show(fm, "fragAlert");
}*/

}

