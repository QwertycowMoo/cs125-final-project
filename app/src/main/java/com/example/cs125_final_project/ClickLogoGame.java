package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class ClickLogoGame extends AppCompatActivity {
    /**Click Counter */
     private int clickCount;
     /**Click Goal */
     private int clickGoal;

    /**timer*/
    private TextView txtTimer;
    /**Number of seconds for the timer*/
    private long timeMillisLeft;

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
        LinearLayout layClickLogo = findViewById(R.id.layClickCSLogo);
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
        timeMillisLeft = 10000;

        playGame();
    }

    private void playGame() {
        CountDownTimer timer = new CountDownTimer(timeMillisLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeMillisLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                endGame(true);
            }
        }.start();
        ImageView imgLogoClick = findViewById(R.id.imgLogoClick);
        imgLogoClick.setOnClickListener(v -> {
            clickCount += 1;
            txtLogoClickCount.setText("Clicked: " + clickCount);
            if (clickCount > clickGoal){
                txtLogoClickCount.setText("You clicked too many times! " + clickCount);
            }
        });
    }
    private void endGame(boolean isEndedByTime) {
        Intent intent = new Intent();
        if (clickCount == clickGoal) {
            intent.putExtra("success", true);
        } else {
            intent.putExtra("success", false);
        }
        setResult(RESULT_OK, intent);

        LinearLayout layClickLogo = findViewById(R.id.layClickCSLogo);
        layClickLogo.setVisibility(View.GONE);
        finish();
    }

    private void updateTimer() {
        int seconds = (int) timeMillisLeft / 1000;
        txtTimer.setText("Time Left: " + seconds);
    }
}