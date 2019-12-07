package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.Random;

public class MpButtonGame extends AppCompatActivity {

    /**for the lives*/
    public final static int HEART_RED_UNICODE = 0x2764;

    public final static int MP_BUTTON_CHANGE_GAME_RES_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Updating coins and lives has to happen through intent or else it just breaks
        Intent intent = getIntent();
        int coins = intent.getIntExtra("coins", -1);
        int lives = intent.getIntExtra("lives", -1);
        TextView txtCoins = findViewById(R.id.txtCoins);
        txtCoins.setText(0 + " Challen Coins");
        TextView txtLives = findViewById(R.id.txtLives);
        String hearts = "";
        for (int i = 0; i < lives; i++) {
            hearts += new String(Character.toChars(HEART_RED_UNICODE));
        }
        txtLives.setText(hearts);

        //Update Game Title
        TextView txtGameTitle = findViewById(R.id.txtGameTitle);
        txtGameTitle.setText("Push your MP!");
        //playGame();

    }
}
