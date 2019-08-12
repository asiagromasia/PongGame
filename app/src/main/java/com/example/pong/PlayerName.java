package com.example.pong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerName extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        button = findViewById(R.id.submitPlayerNameButton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                openLevelOne();

            }
        });

    }
    public void openLevelOne() {
        Intent intent = new Intent(getApplicationContext(),LevelOne.class);
        startActivity(intent);
    }
}