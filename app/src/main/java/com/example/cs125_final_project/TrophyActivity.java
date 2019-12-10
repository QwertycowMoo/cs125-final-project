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
    private final int[] COST = {500, 100, 150, 200, 50, 1, 20};

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
        // Data received from MainActivity
        trophies = getIntent().getBooleanArrayExtra("trophies");
        coins = getIntent().getIntExtra("coins", 0);
        updateCoins(coins);
        // Return button
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

        /** Imageviews for the sters. very important. */
        ImageView ster0 = findViewById(R.id.ster0);
        ImageView ster1 = findViewById(R.id.ster1);
        int complete = 0;
        for (int i = 0; i < trophies.length; i++) {
            if (!trophies[i]) {
                complete++;
                break;
            }
        }
        if (complete == 0) {
            ster0.setVisibility(VISIBLE);
            ster1.setVisibility(VISIBLE);
        } else {
            ster0.setVisibility(INVISIBLE);
            ster1.setVisibility(INVISIBLE);
        }

        /** Textview for setting messages from clicking/earning trophies.
         * this is automatically defaulted to nothing. */
        TextView trophy_message = findViewById(R.id.trophy_message);

        /** On-click Listeners for the trophies. If a trophy has not been collected, but the trophy is
         * clicked and the requirements have been met, the trophy will appear. */
        //__________________________________________________________________________________________
        ImageButton trophy_geoff = findViewById(R.id.trophy_geoff);
        boolean trophy0;
        if (trophies[0]) {
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
                if (coins >= COST[0]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(0);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 500 Challen Coins.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_daniel = findViewById(R.id.trophy_daniel);
        boolean trophy1;
        if (trophies[1]) {
            trophy_daniel.setColorFilter(Color.TRANSPARENT);
            trophy1 = true;
        } else {
            trophy_daniel.setColorFilter(Color.rgb(33, 35, 36));
            trophy1 = false;
        }
        if (trophy1) {
            trophy_daniel.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("Office Captain Daniel Gleason: \nValiant gamer, \nsavior of many MPs.");
            });
        } else {
            trophy_daniel.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                if (coins >= COST[1]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(1);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 100 Challen Coins.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_rima = findViewById(R.id.trophy_rima);
        boolean trophy2;
        if (trophies[2]) {
            trophy_rima.setColorFilter(Color.TRANSPARENT);
            trophy2 = true;
        } else {
            trophy_rima.setColorFilter(Color.rgb(33, 35, 36));
            trophy2 = false;
        }
        if (trophy2) {
            trophy_rima.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("Office Captain Rima Bouhal: \nFrequent forum user, \nenjoys reading CS 125 memes.");
            });
        } else {
            trophy_rima.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                if (coins >= COST[2]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(2);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 150 Challen Coins.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_ben = findViewById(R.id.trophy_ben);
        boolean trophy3;
        if (trophies[3]) {
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
                buy_trophy.setVisibility(INVISIBLE);
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
        boolean trophy4;
        if (trophies[4]) {
            trophy_david.setColorFilter(Color.TRANSPARENT);
            trophy4 = true;
        } else {
            trophy_david.setColorFilter(Color.rgb(33, 35, 36));
            trophy4 = false;
        }
        if (trophy4) {
            trophy_david.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("CA David Ruvinskiy: \nKnight of the Office Hours, \nhas two apps on the app store.");
            });
        } else {
            trophy_david.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                if (coins >= COST[4]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(4);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 50 Challen Coins.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_evan = findViewById(R.id.trophy_evan);
        boolean trophy5;
        if (trophies[5]) {
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
            trophy_evan.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                if (coins >= COST[5]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(5);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 1 Challen Coin.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_kevin = findViewById(R.id.trophy_kevin);
        boolean trophy6;
        if (trophies[6]) {
            trophy_kevin.setColorFilter(Color.TRANSPARENT);
            trophy6 = true;
        } else {
            trophy_kevin.setColorFilter(Color.rgb(33, 35, 36));
            trophy6 = false;
        }
        if (trophy6) {
            trophy_kevin.setOnClickListener(v -> {
                buy_trophy.setVisibility(INVISIBLE);
                trophy_message.setText("Kevin Zhou: \nSoon-to-be CS + Music Major, \nplays Ultimate Frisbee on weekends.");
            });
        } else {
            trophy_kevin.setOnClickListener(view -> {
                buy_trophy.setVisibility(INVISIBLE);
                if (coins >= COST[6]) {
                    buy_trophy.setVisibility(VISIBLE);
                    buy_trophy.setOnClickListener(v -> {
                        buyTrophy(6);
                    });
                }
                trophy_message.setText("* * * LOCKED: * * * \nUnlock with 20 Challen Coins.");
            });
        }
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
                        // subtracting the cost of the trophy,
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
