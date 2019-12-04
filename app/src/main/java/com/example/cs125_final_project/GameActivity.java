package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    private int difficulty;

    public final static int EASY = 1;
    public final static int MEDIUM = 2;
    public final static int HARD = 3;
    public final static int MP2 = 4;
    public final static int SLIDE_CHANGE_GAME_REQ_CODE = 0;

    public final static int COLOR_GREEN = 0xff00ff00;
    public final static int COLOR_RED = 0xffff0000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtCoins = findViewById(R.id.txtCoins);
        txtPassFail = findViewById(R.id.txtPassFail);
        layPassFail = findViewById(R.id.layPassFail);

        difficulty = EASY;
        Intent intentSlidesChange = new Intent(this, SlideChangeGame.class);
        startActivityForResult(intentSlidesChange, SLIDE_CHANGE_GAME_REQ_CODE);
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
                int coinsGained = 10 * difficulty;
                updateCoins(coinsGained);
                txtPassFail.setTextColor(COLOR_GREEN);
                txtPassFail.setText("Pass :)");
            } else {
                txtPassFail.setTextColor(COLOR_RED);
                txtPassFail.setText("Failed!");
            }
            layPassFail.setVisibility(View.VISIBLE);
        }

        //Need to set up a timer then cleanup
        /*
        Intent intentSlidesChange = new Intent(this, SlideChangeGame.class);
        startActivityForResult(intentSlidesChange, SLIDE_CHANGE_GAME_REQ_CODE);
        */
    }

    private void updateCoins(int newCoins) {
        System.out.println("beginning to update coins");
        String coinsText = (String) txtCoins.getText();
        String coinsOnly = coinsText.replaceAll("[^0-9]", "");
        System.out.println(coinsOnly);
        int coins = Integer.parseInt(coinsOnly);
        coins += newCoins;
        txtCoins.setText(String.format(Locale.getDefault(),"Challen Coins: %d", coins));
    }
}
