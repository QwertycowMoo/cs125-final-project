package com.example.cs125_final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** NOTE: Our new android feature for this app is a countdown timer located in GameActivity.java */


public class MainActivity extends AppCompatActivity {
    /** The user's Challen Coins*/
    private int coins = 0;

    /** Array of trophies earned as a boolean[] */
    private int[] trophies;

    private String trophyFile = "trophyInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGame = findViewById(R.id.btnArcadeMode);
        Button btnCredits = findViewById(R.id.btnCredits);
        Button btnTrophy = findViewById(R.id.btnTrophyRoom);
        Button btnReset = findViewById(R.id.btnReset);

        System.out.println("Started Again");

        //Put trophies storage and file access here
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fileReader = this.openFileInput(trophyFile);
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fileReader, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
            fileReader.close();

        } catch (IOException e) {
            //Make a dialog box saying that files were lost so you're restarting
        } finally {
            trophies = new int[7];
            String gameInfo = stringBuilder.toString();
            String[] gInfo = gameInfo.split(",");
            if (gInfo[0] == "") {
                coins = 0;
            } else {
                coins = Integer.parseInt(gInfo[0]);
            }
            updateCoins();
            for (int i = 1; i < gInfo.length; i++) {
                trophies[i - 1] = Integer.parseInt(gInfo[i]);
                System.out.println(trophies[i - 1]);
            }

        }
        btnCredits.setOnClickListener(v -> {
            //coins = coins + 1021;
            Intent intentCredits = new Intent(this, CreditsActivity.class);
            System.out.println(intentCredits);
            startActivity(intentCredits);
            updateCoins();
        });

        btnTrophy.setOnClickListener(v -> {
            Intent intentTrophy = new Intent(this, TrophyActivity.class);
            intentTrophy.putExtra("coins", coins);
            intentTrophy.putExtra("trophies", trophies);
            startActivityForResult(intentTrophy, 2);
        });

        btnGame.setOnClickListener(v -> {
            //Needs to start this activity for result to get the challen coins and also
            //make UI element to keep track of Challen Coins
            Intent intentGame = new Intent(this, GameActivity.class);
            startActivityForResult(intentGame, 0);
        });

        btnReset.setOnClickListener(v -> {
            AlertDialog.Builder alertReset = new AlertDialog.Builder(this);
            alertReset.setMessage("Are you sure you want to reset?" +
                    " This will remove all of your coins and trophies.")
                    .setTitle("Reset")
                    .setPositiveButton("Reset Progress", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            coins = 0;
                            updateCoins();
                            trophies = new int[7];
                        }
                    })
                    .setNegativeButton("Cancel Action", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = alertReset.create();
            dialog.show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            coins += data.getIntExtra("challenCoins", 0);
            System.out.println(coins);
        }
        if (resultCode == 2) {
            coins = data.getIntExtra("coinsToReturn", 0);
            trophies = data.getIntArrayExtra("trophiesToReturn");
        }
        updateCoins();
    }

    private void updateCoins() {
        TextView coinText = findViewById(R.id.txtMainCoins);
        if (coins == 1) {
            coinText.setText(coins + " Challen Coin");
        } else {
            coinText.setText(coins + " Challen Coins");
        }
    }

    @Override
    protected void onStop() {
        String fileInfo;
        StringBuilder fBuilder = new StringBuilder();
        fBuilder.append(coins).append(",");
        for (int i = 0; i < trophies.length; i++) {
            fBuilder.append(trophies[i]);
            if (i != trophies.length - 1) {
                fBuilder.append(",");
            }
        }
        fileInfo = fBuilder.toString();
        System.out.println(fileInfo);
        try {
            FileOutputStream fOutput = this.openFileOutput(trophyFile, this.MODE_PRIVATE);
            fOutput.write(fileInfo.getBytes());
            fOutput.close();
        } catch (IOException e) {

        }

        super.onStop();
    }
}
