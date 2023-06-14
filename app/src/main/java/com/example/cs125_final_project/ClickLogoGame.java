package com.example.cs125_final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class ClickLogoGame extends Game {
    /**Click Counter */
     private int clickCount;
     /**Click Goal */
     private int clickGoal;

     private LinearLayout layClickLogo;
    public static final int CLICK_LOGO_GAME_END_CODE = 1;

    TextView txtLogoClickCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //labels and shtuff
        TextView txtGameTitle = findViewById(R.id.txtGameTitle);
        txtLogoClickCount = findViewById(R.id.txtLogoClickCount);
        txtTimer = findViewById(R.id.txtTimer);
        TextView txtLives = findViewById(R.id.txtLives);
        TextView txtCoins = findViewById(R.id.txtCoins);
        //Show game
        layClickLogo = findViewById(R.id.layClickCSLogo);
        layClickLogo.setVisibility(View.VISIBLE);

        //Game information setup
        Random r = new Random();
        clickCount = 0;
        clickGoal = 10 + r.nextInt(15);
        txtGameTitle.setText("Click the CS125 Logo " + clickGoal + " Times");
        Intent intent = getIntent();
        int lives = intent.getIntExtra("lives", -1);
        int coins = intent.getIntExtra("coins", -1);
        txtCoins.setText("Coins Earned: " + coins);
        GameActivity.updateLives(lives, txtLives);

        //timer setup
        timeMillisLeft = 9000;

        playGame();
    }

    private void playGame() {
        setupGame();
        ImageView imgLogoClick = findViewById(R.id.imgLogoClick);
        imgLogoClick.setOnClickListener(v -> {
            clickCount += 1;
            txtLogoClickCount.setText("Clicked: " + clickCount);
            if (clickCount > clickGoal){
                txtLogoClickCount.setText("You clicked too many times! " + clickCount);
            }
        });
    }

    @Override
    public boolean checkGame() {
        boolean success = false;
        if (clickCount == clickGoal) {
            success = true;
        }
        return success;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        layClickLogo.setVisibility(View.GONE);
    }
}
