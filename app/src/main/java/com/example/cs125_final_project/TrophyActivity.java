package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class TrophyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);
        Button trophy_back = findViewById(R.id.btnBack);
        trophy_back.setOnClickListener(v -> {
            finish();
        });
    }
}
