package com.example.pong;

//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;



public class EditNameDialogFragment extends DialogFragment {
    private EditText mEditText;
    public EditNameDialogFragment() {
        // Empty constructor required for DialogFragment
    }


    public static EditNameDialogFragment newInstance(String title) {
        EditNameDialogFragment frag = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.GameOver)
                .setPositiveButton(R.string.StartAgain, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // user clicked Start again button
                    }
                })
                .setNegativeButton(R.string.Exit, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}








//public class GameOver1 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // setContentView(R.layout.activity_game_over);
//        //openDialog();
//    }
//
////    public void openDialog() {
//        View view = LayoutInflater.from(GameOver).inflate(R.layout.activity_game_over, null);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameOver.this);
//        alertDialogBuilder.setMessage("Game Over!")
//                .setView(view)
//                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // Toast.makeText(GameView.this, "You clicked yes button", Toast.LENGTH_LONG).show();
//                        //setupAndRestart();
//                    }
//                })
//                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //safe score
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//
//    }




//public class GameOver extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // setContentView(R.layout.activity_game_over);
//        openDialog();
//    }
//
//    public void openDialog() {
//        View view = LayoutInflater.from(GameOver.this).inflate(R.layout.activity_game_over, null);
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GameOver.this);
//        alertDialogBuilder.setMessage("Game Over!")
//                .setView(view)
//                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        // Toast.makeText(GameView.this, "You clicked yes button", Toast.LENGTH_LONG).show();
//                        //setupAndRestart();
//                    }
//                })
//                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //safe score
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//
//    }
//}
