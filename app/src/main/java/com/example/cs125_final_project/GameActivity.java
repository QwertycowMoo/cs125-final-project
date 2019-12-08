package com.example.cs125_final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    /** Array of all the games, maybe later put in options to select games and such */
    private Class[] games = new Class[] {SlideChangeGame.class,
                                        ClickLogoGame.class,
                                        SmashBugsGame.class,
                                        QuestionsGame.class};
    private int gameSelect;
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
    private CountDownTimer timer;

    public static final int QUIT_GAME_END_CODE = -1;


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
        timer = new CountDownTimer(passFailTimerMillisLeft, 1000) {
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

            switch (requestCode) {
                case SlideChangeGame.SLIDE_CHANGE_GAME_END_CODE:
                    if (success) {
                        coins += 2;
                    }
                    break;
                case ClickLogoGame.CLICK_LOGO_GAME_END_CODE:
                    if (success) {
                        coins += 3;
                    }
                    break;
                case SmashBugsGame.SMASH_BUGS_GAME_END_CODE:
                    if (success) {
                        coins += 2;
                    }
                    break;
                case QuestionsGame.QUESTIONS_GAME_END_CODE:
                    if (success) {
                        coins += 1;
                    }
                    break;
            }


            updateCoins();
            updateLives(lives, txtLives);
            timer = new CountDownTimer(passFailTimerMillisLeft, 1000) {
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
        else {
            endFullGame();
        }


    }

    private int updateCoins() {
        txtCoins.setText(String.format(Locale.getDefault(),"Coins Earned: %d", coins));
        return coins;
    }

    private void startNewMiniGame() {
        Random r = new Random();
        gameSelect = r.nextInt(4);

        Intent gameIntent = new Intent(this, games[gameSelect]);
        addInfoToMiniGameIntent(gameIntent);
        startActivityForResult(gameIntent, gameSelect);
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
        timer.cancel();
        Intent intent = new Intent();
        intent.putExtra("challenCoins", coins);
        setResult(0, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Do you really want to quit?")
                .setTitle("Quitting?")
                .setPositiveButton(R.string.finish_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        endFullGame();
                    }
                })
                .setNegativeButton(R.string.continue_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.start();
                    }
                });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }
}
