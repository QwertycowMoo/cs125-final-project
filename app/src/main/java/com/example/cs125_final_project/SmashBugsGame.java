package com.example.cs125_final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class SmashBugsGame extends AppCompatActivity{
    private boolean[][] bugs;

    private LinearLayout laySmashBugs;
    /**score and textlabel for score*/
    private int score;
    private TextView txtBugsLeft;
    private int selectedBugRow;
    private int selectedBugCol;

    /**timer*/
    long timeMillisLeft;
    private TextView txtTimer;
    private CountDownTimer timer;

    /**constants*/
    public final int TOTAL_BUGS = 12;
    public static final int SMASH_BUGS_GAME_END_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        laySmashBugs = findViewById(R.id.laySmashBugs);
        laySmashBugs.setVisibility(View.VISIBLE);
        txtTimer = findViewById(R.id.txtTimer);
        txtBugsLeft = findViewById(R.id.txtBugsLeft);
        ImageView imgBug0_0 = findViewById(R.id.imgBug0_0);
        ImageView imgBug1_0 = findViewById(R.id.imgBug1_0);
        ImageView imgBug2_0 = findViewById(R.id.imgBug2_0);
        ImageView imgBug3_0 = findViewById(R.id.imgBug3_0);
        ImageView imgBug0_1 = findViewById(R.id.imgBug0_1);
        ImageView imgBug1_1 = findViewById(R.id.imgBug1_1);
        ImageView imgBug2_1 = findViewById(R.id.imgBug2_1);
        ImageView imgBug3_1 = findViewById(R.id.imgBug3_1);
        ImageView imgBug0_2 = findViewById(R.id.imgBug0_2);
        ImageView imgBug1_2 = findViewById(R.id.imgBug1_2);
        ImageView imgBug2_2 = findViewById(R.id.imgBug2_2);
        ImageView imgBug3_2 = findViewById(R.id.imgBug3_2);
        ImageView imgBug0_3 = findViewById(R.id.imgBug0_3);
        ImageView imgBug1_3 = findViewById(R.id.imgBug1_3);
        ImageView imgBug2_3 = findViewById(R.id.imgBug2_3);
        ImageView imgBug3_3 = findViewById(R.id.imgBug3_3);
        imgBug0_0.setOnClickListener(v -> processBugClick(0,0));
        imgBug1_0.setOnClickListener(v -> processBugClick(1,0));
        imgBug2_0.setOnClickListener(v -> processBugClick(2,0));
        imgBug3_0.setOnClickListener(v -> processBugClick(3,0));
        imgBug0_1.setOnClickListener(v -> processBugClick(0,1));
        imgBug1_1.setOnClickListener(v -> processBugClick(1,1));
        imgBug2_1.setOnClickListener(v -> processBugClick(2,1));
        imgBug3_1.setOnClickListener(v -> processBugClick(3,1));
        imgBug0_2.setOnClickListener(v -> processBugClick(0,2));
        imgBug1_2.setOnClickListener(v -> processBugClick(1,2));
        imgBug2_2.setOnClickListener(v -> processBugClick(2,2));
        imgBug3_2.setOnClickListener(v -> processBugClick(3,2));
        imgBug0_3.setOnClickListener(v -> processBugClick(0,3));
        imgBug1_3.setOnClickListener(v -> processBugClick(1,3));
        imgBug2_3.setOnClickListener(v -> processBugClick(2,3));
        imgBug3_3.setOnClickListener(v -> processBugClick(3,3));

        bugs = new boolean[][] {{false, false, false, false},
                                {false, false, false, false},
                                {false, false, false, false},
                                {false, false, false, false}};
        //setup Lives and Coins earned
        Intent intent = getIntent();
        int lives = intent.getIntExtra("lives", -1);
        int coins = intent.getIntExtra("coins", -1);
        TextView txtLives = findViewById(R.id.txtLives);
        TextView txtCoins = findViewById(R.id.txtCoins);
        GameActivity.updateLives(lives, txtLives);
        txtCoins.setText("Coins Earned: " + coins);


        TextView txtGameTitle = findViewById(R.id.txtGameTitle);
        txtGameTitle.setText("Squash the Bugs in the Code!");
        //Timer and begin game
        timeMillisLeft = 10000;
        playGame();
    }

    private void processBugClick(int row, int col) {
        if (selectedBugCol == col && selectedBugRow == row){
            bugVisible(row, col, View.INVISIBLE);
            score++;
            bugs[row][col] = true;
            newBug();
        }
    }


    private void playGame() {
        timer = new CountDownTimer(timeMillisLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeMillisLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                endGame(RESULT_OK);
            }
        }.start();
        newBug();
    }
    private void newBug() {
        if (score != TOTAL_BUGS) {
            txtBugsLeft.setText(TOTAL_BUGS - score + " bugs left, squash them!");
            Random r = new Random();
            int row = r.nextInt(4);
            int col = r.nextInt(4);
            while (bugs[row][col] == true) {
                row = r.nextInt(4);
                col = r.nextInt(4);
            }
            selectedBugRow = row;
            selectedBugCol = col;
            bugVisible(row, col, View.VISIBLE);
            System.out.println(row + " " + col);
        } else {
            endGame(RESULT_OK);
        }
    }
    /* To Debug Ha get it
    private void printBugsArray() {
        String output = "";
        for (int i = 0; i < bugs.length; i++) {
            for (int j = 0; j < bugs[i].length; j++) {
                output += bugs[i][j];
            }
            System.out.println(output);
            output = "";
        }

    }
     */
    private void bugVisible(int row, int col, int visibleCode) {
        //Please do not look at this code Kevin made it if you want to change it ask Kevin first I'm sorry
        switch (row) {
            case 0:
                switch (col) {
                    case 0:
                        ImageView bug1_1 = findViewById(R.id.imgBug0_0);
                        bug1_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug1_2 = findViewById(R.id.imgBug0_1);
                        bug1_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug1_3 = findViewById(R.id.imgBug0_2);
                        bug1_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug1_4 = findViewById(R.id.imgBug0_3);
                        bug1_4.setVisibility(visibleCode);
                        break;
                }
                break;
            case 1:
                switch (col) {
                    case 0:
                        ImageView bug2_1 = findViewById(R.id.imgBug1_0);
                        bug2_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug2_2 = findViewById(R.id.imgBug1_1);
                        bug2_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug2_3 = findViewById(R.id.imgBug1_2);
                        bug2_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug2_4 = findViewById(R.id.imgBug1_3);
                        bug2_4.setVisibility(visibleCode);
                        break;
                }
                break;
            case 2:
                switch (col) {
                    case 0:
                        ImageView bug3_1 = findViewById(R.id.imgBug2_0);
                        bug3_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug3_2 = findViewById(R.id.imgBug2_1);
                        bug3_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug3_3 = findViewById(R.id.imgBug2_2);
                        bug3_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug3_4 = findViewById(R.id.imgBug2_3);
                        bug3_4.setVisibility(visibleCode);
                        break;
                }
                break;
            case 3:
                switch (col) {
                    case 0:
                        ImageView bug4_1 = findViewById(R.id.imgBug3_0);
                        bug4_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug4_2 = findViewById(R.id.imgBug3_1);
                        bug4_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug4_3 = findViewById(R.id.imgBug3_2);
                        bug4_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug4_4 = findViewById(R.id.imgBug3_3);
                        bug4_4.setVisibility(visibleCode);
                        break;
                }
                break;
        }

    }

    private void updateTimer() {
        int seconds = (int) timeMillisLeft / 1000;
        txtTimer.setText("Time Left: " + seconds);
    }

    private void endGame(int endCode) {
        boolean success = false;
        if (score == TOTAL_BUGS) {
            success = true;
        }
        Intent intent = new Intent();
        intent.putExtra("success", success);
        setResult(endCode, intent);
        laySmashBugs.setVisibility(View.GONE);
        TextView txtSquashSuccess = findViewById(R.id.txtBugsLeft);
        txtSquashSuccess.setVisibility(View.INVISIBLE);
        finish();
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Do you really want to quit?")
                .setTitle("Quitting?")
                .setPositiveButton(R.string.finish_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        endGame(RESULT_CANCELED);
                    }
                })
                .setNegativeButton(R.string.continue_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer.start();
                    }
                });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

}
