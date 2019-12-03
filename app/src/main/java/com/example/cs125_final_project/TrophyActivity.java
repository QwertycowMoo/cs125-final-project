package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

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
    }
}
