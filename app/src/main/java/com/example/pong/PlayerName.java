package com.example.pong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerName extends AppCompatActivity {
  //public static final String EXTRA_MESSAGE = "com.example.pong.MESSAGE";

    private Button button;
   // TextView PlayerNameValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        button = findViewById(R.id.submitPlayerNameButton);
        button.setOnClickListener(new View.OnClickListener( ) {

            @Override
            public void onClick(View v) {
              //  String playerName = PlayerNameValue.getText( ).toString( );
              //  Intent startButtonIntent;
              //  startButtonIntent = new Intent(getApplicationContext( ), LevelOne.class);
              //  startButtonIntent.putExtra("playerName", playerName);
              //  sendMessage(view);
              //  startActivity(startButtonIntent);
                openLevelOne( );


            }
        });
    }


  /* public void playerName(){
       String playerName = PlayerNameValue.getText().toString( );
       TextView playerName = findViewById(R.id.PlayerNameValue);

   } ;*/

   public void openLevelOne(){
        Intent intent = new Intent(this, LevelOne.class);

       // TextView playerName = findViewById(R.id.PlayerNameValue);
       // String name = PlayerNameValue.getText().toString();
       // intent.putExtra("name", name);
       startActivity(intent);
    }
    
    /*public void openLevelOne() {
        Intent intent = new Intent(getApplicationContext(),LevelOne.class);
        TextView PlayerNameValue = (TextView) findViewById(R.id.PlayerNameValue);
        String gamePlayer = PlayerNameValue.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, gamePlayer);
        startActivity(intent);
    }*/
}
