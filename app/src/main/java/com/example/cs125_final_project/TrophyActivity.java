package com.example.cs125_final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class TrophyActivity extends AppCompatActivity {

    /** Boolean array for holding trophies, (whether or not they are unlocked) */
    private boolean[] trophies;

    /** Array of ints storing the costs of certain trophies */
    private final int[] COST = {0, 0, 0, 200, 0, 0, 0};

    /** Integer to hold the number of coins the user has. Will be passed back to MainActivity. */
    private int coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        ImageButton trophy_back = findViewById(R.id.button_back);
        trophy_back.setVisibility(VISIBLE);
        Button buy_trophy = findViewById(R.id.buy_trophy);
        buy_trophy.setVisibility(INVISIBLE);
        trophies = getIntent().getBooleanArrayExtra("trophies");
        coins = getIntent().getIntExtra("coins", 0);
        updateCoins(coins);
        trophy_back.setOnClickListener(v -> {
            trophy_back.setVisibility(INVISIBLE);
            // New intent used to send trophy array, coins back to main activity.
            // coinsToReturn update the user's current coins, while the array stores information about the trophies.
            Intent saveData = new Intent(this, MainActivity.class);
            saveData.putExtra("coinsToReturn", coins);
            saveData.putExtra("trophiesToReturn", trophies);
            setResult(2, saveData);
            finish();
        });

        /** Textview for setting messages from clicking/earning trophies.
         * this is automatically defaulted to nothing. */
        TextView trophy_message = findViewById(R.id.trophy_message);

        /** On-click Listeners for the trophies. If a trophy has not been collected, but the trophy is
         * clicked and the requirements have been met, the trophy will appear. */
        //__________________________________________________________________________________________
        ImageButton trophy_geoff = findViewById(R.id.trophy_geoff);
        boolean trophy0;
        if (coins >= 1000) {
            trophies[0] = true;
        }
        if (trophies[0] == true) {
            trophy_geoff.setColorFilter(Color.TRANSPARENT);
            trophy0 = true;
        } else {
            trophy_geoff.setColorFilter(Color.rgb(33, 35, 36));
            trophy0 = false;
        }
        if (trophy0) {
            trophy_geoff.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("Geoff Challen: \nCS 125 Professor, \nZuccbot's former TA.");
            });
        } else {
            trophy_geoff.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("* * * LOCKED: * * * \nOpen the Trophy Room after \naccumulating 1,000 Challen Coins.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_daniel = findViewById(R.id.trophy_daniel);
        trophy_daniel.setOnClickListener(v -> {
            buy_trophy.setVisibility(INVISIBLE);
            trophy_message.setText("Office Captain Daniel Gleason: \nValiant gamer, \nsavior of many MPs.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_rima = findViewById(R.id.trophy_rima);
        trophy_rima.setOnClickListener(v -> {
            buy_trophy.setVisibility(INVISIBLE);
            trophy_message.setText("Office Captain Rima Bouhal: \nFrequent forum user, \nenjoys reading CS 125 memes.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_ben = findViewById(R.id.trophy_ben);
        boolean trophy3;
        if (trophies[3] == true) {
            trophy_ben.setColorFilter(Color.TRANSPARENT);
            trophy3 = true;
        } else {
            trophy_ben.setColorFilter(Color.rgb(33, 35, 36));
            trophy3 = false;
        }
        if (trophy3) {
            trophy_ben.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("Head CA Ben Nordick: \nSecond in command, \nmade a successful bus app.");
            });
        } else {
            trophy_ben.setOnClickListener(view -> {
                if (coins >= COST[3]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(3);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 200 Challen Coins.");
            });
        }


        //__________________________________________________________________________________________

        ImageButton trophy_david = findViewById(R.id.trophy_david);
        trophy_david.setOnClickListener(v -> {
            buy_trophy.setVisibility(INVISIBLE);
            trophy_message.setText("CA David Ruvinskiy: \nKnight of the Office Hours, \nhas two apps on the app store.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_evan = findViewById(R.id.trophy_evan);
        boolean trophy5;
        if (coins == 1) {
            trophies[5] = true;
        }
        if (trophies[5] == true) {
            trophy_evan.setColorFilter(Color.TRANSPARENT);
            trophy5 = true;
        } else {
            trophy_evan.setColorFilter(Color.rgb(33, 35, 36));
            trophy5 = false;
        }
        if (trophy5) {
            trophy_evan.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("Evan Matthews: \nCS + Music Major, \nhas a huge sweater collection.");
            });
        } else {
            trophy_evan.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("* * * LOCKED: * * * \nOpen the Trophy Room with \nonly a single Challen coin.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_kevin = findViewById(R.id.trophy_kevin);
        trophy_kevin.setOnClickListener(v -> {
            buy_trophy.setVisibility(INVISIBLE);
            trophy_message.setText("Kevin Zhou: \nSoon-to-be CS + Music Major, \nplays Ultimate Frisbee on weekends.");
        });
    }

    private void updateCoins(int coins) {
        TextView coinText = findViewById(R.id.trophy_coins);
        if (coins == 1) {
            coinText.setText(coins + " Challen Coin");
        } else {
            coinText.setText(coins + " Challen Coins");
        }
    }

    public void buyTrophy(int i) {
        Button buy_trophy = findViewById(R.id.buy_trophy);
        AlertDialog.Builder alertBuy = new AlertDialog.Builder(this);
        alertBuy.setMessage("Are you sure you want to unlock this trophy?")
                .setTitle("Unlock?")
                .setPositiveButton("Unlock Trophy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buy_trophy.setVisibility(INVISIBLE);
                        trophies[i] = true;
                        // Updates coins by subtracting the cost of the trophy,
                        // then recreates the current intent with updated extras.
                        coins -= COST[i];
                        getIntent().putExtra("coins", coins);
                        getIntent().putExtra("trophies", trophies);
                        recreate();
                        // The trophy silhouette is changed upon recreating the intent with an updated trophy array.
                    }
                })
                .setNegativeButton("Cancel Action", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        buy_trophy.setVisibility(INVISIBLE);
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = alertBuy.create();
        dialog.show();
    }
}
