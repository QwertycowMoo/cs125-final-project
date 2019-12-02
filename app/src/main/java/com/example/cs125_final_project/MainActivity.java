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

        Button btnGame = (Button) findViewById(R.id.btnArcadeMode);
        Button btnTrophyRoom = (Button) findViewById(R.id.btnTrophyRoom);
        Button btnCredits = (Button) findViewById(R.id.btnCredits);
        btnTrophyRoom.setOnClickListener(v -> {
            Intent trophyRoomIntent = new Intent();
            
        });
    }
}
