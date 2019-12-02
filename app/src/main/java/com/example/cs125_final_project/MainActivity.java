package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGame = findViewById(R.id.btnArcadeMode);
        Button btnCredits = findViewById(R.id.btnCredits);
        Button btnTrophy = findViewById(R.id.btnTrophyRoom);

        btnCredits.setOnClickListener(v -> {
            Intent intentCredits = new Intent(this, CreditsActivity.class);
            System.out.println(intentCredits);
            startActivity(intentCredits);
        });


        btnTrophy.setOnClickListener(v -> {
            Intent intentTrophy = new Intent(this, TrophyActivity.class);
            startActivity(intentTrophy);
        });

        btnGame.setOnClickListener(v -> {
            Intent intentGame = new Intent(this, GameActivity.class);
            startActivity(intentGame);
        });
    }
}
