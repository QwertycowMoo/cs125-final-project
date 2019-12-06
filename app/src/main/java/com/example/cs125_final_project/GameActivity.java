package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {
    /** Array of all the games, maybe later put in options to select games and such */
    private Class[] games = new Class[] {SlideChangeGame.class,
                                        ClickLogoGame.class};
    /**Textbox holding the challen coins*/
    private TextView txtCoins;
    /**Tells the player if they failed or passed after their minigame */
    private TextView txtPassFail;
    private LinearLayout layPassFail;

    /**Fail message uses the game title textview*/
    private TextView txtGameTitle;

    /**lives*/
    private int lives;
    public final static int HEART_RED_UNICODE = 0x2764;
    private TextView txtLives;
    /**coins*/
    private int coins;
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
        txtGameTitle = findViewById(R.id.txtGameTitle);
        //Set up lives and coins
        lives = 3;
        updateLives(lives, txtLives);
        coins = 0;

        layPassFail.setVisibility(View.VISIBLE);
        txtPassFail.setTextColor(getResources().getColor(R.color.topGradient));
        txtPassFail.setText("Ready?");
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
        if (resultCode == RESULT_OK) {
            boolean success = data.getBooleanExtra("success", false);
            if (success) {
                txtPassFail.setTextColor(COLOR_GREEN);
                txtPassFail.setText("Pass :)");
            } else {
                txtPassFail.setTextColor(COLOR_RED);
                lives--;
                if (lives > 0) {
                    txtPassFail.setText("Failed!");
                } else {
                    txtPassFail.setText("Game Over");
                }

            }
            layPassFail.setVisibility(View.VISIBLE);

            if (requestCode == SlideChangeGame.SLIDE_CHANGE_GAME_END_CODE) {
                // Do something that depends on the result of that request
                if (success) {
                    coins += 2;
                }

            } else if (requestCode == ClickLogoGame.CLICK_LOGO_GAME_END_CODE) {
                if (success) {
                    coins += 3;
                }
            }
        }

        updateCoins();
        updateLives(lives, txtLives);
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

    private int updateCoins() {
        txtCoins.setText(String.format(Locale.getDefault(),"Coins Earned: %d", coins));
        return coins;
    }

    private void startNewMiniGame() {
        Random r = new Random();
        int gameSelect = r.nextInt(2);

        Intent intentSlidesChange = new Intent(this, games[gameSelect]);
        addInfoToMiniGameIntent(intentSlidesChange);
        startActivityForResult(intentSlidesChange, gameSelect);
    }

    private void addInfoToMiniGameIntent(Intent intent) {
        intent.putExtra("coins", coins);
        intent.putExtra("lives", lives);
    }

    /**
     * Static method that updates the lives textbox anywhere
     * @param lives
     * @param txtLives
     */
    public static void updateLives(int lives, TextView txtLives) {
        String hearts = "";
        for (int i = 0; i < lives; i++) {
            hearts += new String(Character.toChars(HEART_RED_UNICODE));
        }
        txtLives.setText(hearts);

    }

    /**
     * Ends the entire game
     */
    private void endFullGame() {
        Intent intent = new Intent();
        intent.putExtra("challenCoins", coins);
        setResult(0, intent);
        finish();
    }
}
