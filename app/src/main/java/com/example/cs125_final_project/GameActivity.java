package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {
    /** Array of all the games, maybe later put in options to select games and such */
    private Game[] games;
    /**Textbox holding the challen coins*/
    private TextView txtCoins;
    /**Tells the player if they failed or passed after their minigame */
    private TextView txtPassFail;
    /**layout containting the pass fail message */
    private LinearLayout layPassFail;
    /**difficulty determines time/may get set to the minigame through an intent to change the game */
    private int difficulty;

    /**lives*/
    private int lives;
    private TextView txtLives;
    public final static int HEART_RED_UNICODE = 0x2764;

    /**coins*/
    private int coins;

    public final static int EASY = 1;
    public final static int MEDIUM = 2;
    public final static int HARD = 3;
    public final static int MP2 = 4;
    public final static int SLIDE_CHANGE_GAME_REQ_CODE = 0;
    /**colors*/
    public final static int COLOR_GREEN = 0xff00ff00;
    public final static int COLOR_RED = 0xffff0000;
    /**timer*/
    private long passFailTimerMillisLeft = TIMER_RESET;
    public final static long TIMER_RESET = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Set up textviews and such for manipulation
        txtCoins = findViewById(R.id.txtCoins);
        txtPassFail = findViewById(R.id.txtPassFail);
        layPassFail = findViewById(R.id.layPassFail);
        txtLives = findViewById(R.id.txtLives);

        //Set up lives and coins
        lives = 2;
        updateLives();
        coins = 0;

        difficulty = EASY;
        CountDownTimer timer = new CountDownTimer(passFailTimerMillisLeft, 1000) {
            @Override
            public void onTick(long l) {
                passFailTimerMillisLeft = l;

            }

            @Override
            public void onFinish() {
                //Go to a new game
                if (lives > 0) {
                    startNewMiniGame();
                    passFailTimerMillisLeft = TIMER_RESET;
                } else {
                    endFullGame();
                }

            }
        }.start();
        //startNewMiniGame();
    }

    /**
     * Invoked by the Android system when a request launched by startActivityForResult completes.
     * @param requestCode the request code passed by to startActivityForResult
     * @param resultCode a value indicating how the request finished (e.g. completed or canceled)
     * @param data an Intent containing results (e.g. as a URI or in extras)
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SLIDE_CHANGE_GAME_REQ_CODE) {
            // Do something that depends on the result of that request
            boolean success = data.getBooleanExtra("success", false);
            if (success) {
                coins += 1 * difficulty;
                txtPassFail.setTextColor(COLOR_GREEN);
                txtPassFail.setText("Pass :)");
            } else {
                txtPassFail.setTextColor(COLOR_RED);
                txtPassFail.setText("Failed!");
                lives--;
            }
            layPassFail.setVisibility(View.VISIBLE);
        }

        updateCoins();
        updateLives();
        CountDownTimer timer = new CountDownTimer(passFailTimerMillisLeft, 1000) {
            @Override
            public void onTick(long l) {
                passFailTimerMillisLeft = l;
                System.out.println(passFailTimerMillisLeft);
            }

            @Override
            public void onFinish() {
                //Go to a new game
                if (lives > 0) {
                    startNewMiniGame();
                    passFailTimerMillisLeft = TIMER_RESET;
                } else {
                    endFullGame();
                }

            }
        }.start();



    }

    private void updateCoins() {
        switch(coins) {
            case 1:
                txtCoins.setText(coins + " Challen Coin");
            default:
                txtCoins.setText(coins + " Challen Coins");
        }
    }

    private void startNewMiniGame() {
        Intent intentSlidesChange = new Intent(this, SlideChangeGame.class);
        addInfoToMiniGameIntent(intentSlidesChange);
        startActivityForResult(intentSlidesChange, SLIDE_CHANGE_GAME_REQ_CODE);
    }

    private void addInfoToMiniGameIntent(Intent intent) {
        intent.putExtra("coins", coins);
        intent.putExtra("lives", lives);
    }
    private void updateLives() {
        String hearts = "";
        for (int i = 0; i < lives; i++) {
            hearts += new String(Character.toChars(HEART_RED_UNICODE));
        }
        txtLives.setText(hearts);

    }

    private void endFullGame() {
        Intent intent = new Intent();
        intent.putExtra("challenCoins", coins);
        setResult(0, intent);
        finish();
    }
}
