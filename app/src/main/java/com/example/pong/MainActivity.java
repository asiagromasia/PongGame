package com.example.pong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  openDialog();
    }

    public void Player1(View view) {
        setContentView(R.layout.activity_player_name);
       Intent intent = new Intent(this, PlayerName.class);
       //startActivity(new Intent(this,PlayerName.class));
    }

    public void Player2(View view) {
        setContentView(R.layout.activity_player_name);
        //  Intent intent = new Intent(this, PlayerName.class);
        startActivity(new Intent(this,PlayerName.class));
    }


    /* *** This is a pop up window at the end of the level*****

    public void openDialog(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Congrats!You can go to next level!");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No, exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }*/

}