package com.example.cs125_final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    /**Coins: needs to find a way to store coins after app is closed*/
    private int coins;

    /** Array of trophies earned as a boolean[] */
    private boolean[] trophies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGame = findViewById(R.id.btnArcadeMode);
        Button btnCredits = findViewById(R.id.btnCredits);
        Button btnTrophy = findViewById(R.id.btnTrophyRoom);

        // Initializes coins to 0... will be replaced with JSON I/O stuff
        coins = 0;
        trophies = new boolean[7];

        btnCredits.setOnClickListener(v -> {
            Intent intentCredits = new Intent(this, CreditsActivity.class);
            System.out.println(intentCredits);
            startActivity(intentCredits);
        });


        btnTrophy.setOnClickListener(v -> {
            Intent intentTrophy = new Intent(this, TrophyActivity.class);
            intentTrophy.putExtra("coins", coins);
            intentTrophy.putExtra("trophies", trophies);
            startActivity(intentTrophy);
        });

        btnGame.setOnClickListener(v -> {
            //Needs to start this activity for result to get the challen coins and also
            //make UI element to keep track of Challen Coins
            Intent intentGame = new Intent(this, GameActivity.class);
            startActivityForResult(intentGame, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            coins += data.getIntExtra("challenCoins", 0);
            System.out.println(coins);
        }
        updateCoins();
    }

    private void updateCoins() {
        TextView coinText = findViewById(R.id.txtMainCoins);
        if (coins == 1) {
            coinText.setText(coins + " Challen Coin");
            trophies[5] = true;
        } else {
            coinText.setText(coins + " Challen Coins");
        }
    }
}
