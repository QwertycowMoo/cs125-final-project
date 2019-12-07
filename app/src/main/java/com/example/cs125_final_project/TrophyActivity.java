package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

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
        trophy_back.setOnClickListener(v -> {
            trophy_back.setVisibility(INVISIBLE);
            finish();
        });

        /** Textview for setting messages from clicking/earning trophies.
         * this is automatically defaulted to nothing. */
        TextView trophy_message = findViewById(R.id.trophy_message);
        trophy_message.setText("test message");
        /** On-click Listeners for the trophies. If a trophy has not been collected, but the trophy is
         * clicked and the requirements have been met, the trophy will appear. */

        ImageButton trophy_geoff = findViewById(R.id.trophy_geoff);
        trophy_geoff.setOnClickListener(view -> {
            trophy_message.setText("Geoff Challen: \nCS 125 Professor, \nZuccbot's TA at Harvard.");
        });

        ImageButton trophy_daniel = findViewById(R.id.trophy_daniel);
        trophy_daniel.setOnClickListener(v -> {
            trophy_message.setText("Office Captain Daniel Gleason: \nValiant gamer, \nsavior of many MPs.");
        });

        ImageButton trophy_rima = findViewById(R.id.trophy_rima);
        trophy_rima.setOnClickListener(v -> {
            trophy_message.setText("Office Captain Rima: \nMaking office hours run \nsmoother since 2019.");
        });

        ImageButton trophy_ben = findViewById(R.id.trophy_ben);
        trophy_ben.setOnClickListener(v -> {
            trophy_message.setText("Head CA Ben Nordick: \nSecond command, \nalso made a bus app.");
        });

        ImageButton trophy_david = findViewById(R.id.trophy_david);
        trophy_david.setOnClickListener(v -> {
            trophy_message.setText("CA David Ruvinskiy: \nKnight of the Office Hours, \nalso has apps on the app store.");
        });

        ImageButton trophy_evan = findViewById(R.id.trophy_evan);
        trophy_evan.setOnClickListener(v -> {
            trophy_message.setText("Evan Matthews: \nCS + Music Major, \nhas a huge sweater collection.");
        });

        ImageButton trophy_kevin = findViewById(R.id.trophy_kevin);
        trophy_kevin.setOnClickListener(v -> {
            trophy_message.setText("Kevin Zhou: \nSoon-to-be CS + Music Major, \nplays Ultimate Frisbee on weekends.");
        });
    }
}
