package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class TrophyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        ImageButton trophy_back = findViewById(R.id.button_back);
        trophy_back.setVisibility(VISIBLE);
        int coins = getIntent().getIntExtra("coins", 0);
        boolean[] trophies = getIntent().getBooleanArrayExtra("trophies");
        updateCoins(coins);
        trophy_back.setOnClickListener(v -> {
            trophy_back.setVisibility(INVISIBLE);
            finish();
        });

        /** Textview for setting messages from clicking/earning trophies.
         * this is automatically defaulted to nothing. */
        TextView trophy_message = findViewById(R.id.trophy_message);
        /** On-click Listeners for the trophies. If a trophy has not been collected, but the trophy is
         * clicked and the requirements have been met, the trophy will appear. */

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
                trophy_message.setText("Geoff Challen: \nCS 125 Professor, \nZuccbot's TA at Harvard.");
            });
        } else {
            trophy_geoff.setOnClickListener(view -> {
                trophy_message.setText("* * * LOCKED: * * * \nOpen the Trophy Room after \naccumulating 1,000 Challen Coins.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_daniel = findViewById(R.id.trophy_daniel);
        trophy_daniel.setOnClickListener(v -> {
            trophy_message.setText("Office Captain Daniel Gleason: \nValiant gamer, \nsavior of many MPs.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_rima = findViewById(R.id.trophy_rima);
        trophy_rima.setOnClickListener(v -> {
            trophy_message.setText("Office Captain Rima: \nMaking office hours run \nsmoother since 2019.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_ben = findViewById(R.id.trophy_ben);
        trophy_ben.setOnClickListener(v -> {
            trophy_message.setText("Head CA Ben Nordick: \nSecond command, \nmade a successful bus app.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_david = findViewById(R.id.trophy_david);
        trophy_david.setOnClickListener(v -> {
            trophy_message.setText("CA David Ruvinskiy: \nKnight of the Office Hours, \nhas two apps on the app store.");
        });

        //__________________________________________________________________________________________

        ImageButton trophy_evan = findViewById(R.id.trophy_evan);
        boolean trophy5;
        if (coins == 1 || trophies[5] == true) {
            trophy_evan.setColorFilter(Color.TRANSPARENT);
            trophy5 = true;
        } else {
            trophy_evan.setColorFilter(Color.rgb(33, 35, 36));
            trophy5 = false;
        }
        if (trophy5) {
            trophy_evan.setOnClickListener(v -> {
                trophy_message.setText("Evan Matthews: \nCS + Music Major, \nhas a huge sweater collection.");
            });
        } else {
            trophy_evan.setOnClickListener(v -> {
                trophy_message.setText("* * * LOCKED: * * * \nOpen the Trophy Room with \nonly a single Challen coin.");
            });
        }

        //__________________________________________________________________________________________

        ImageButton trophy_kevin = findViewById(R.id.trophy_kevin);
        trophy_kevin.setOnClickListener(v -> {
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
}
