package com.example.cs125_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.String;

public class SmashBugsGame extends AppCompatActivity implements View.OnClickListener{
    private boolean[][] bugs;

    private LinearLayout laySmashBugs;
    private int score;
    private int[] selectedBug;

    /**timer*/
    long timeMillisLeft;
    TextView txtTimer;

    /**constants*/
    public final int TOTAL_BUGS = 16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        laySmashBugs = findViewById(R.id.laySmashBugs);
        laySmashBugs.setVisibility(View.VISIBLE);
        txtTimer = findViewById(R.id.txtTimer);
        ImageView imgBug1_1 = findViewById(R.id.imgBug1_1);
        ImageView imgBug1_2 = findViewById(R.id.imgBug1_2);
        ImageView imgBug1_3 = findViewById(R.id.imgBug1_3);
        ImageView imgBug1_4 = findViewById(R.id.imgBug1_4);
        ImageView imgBug2_1 = findViewById(R.id.imgBug2_1);
        ImageView imgBug2_2 = findViewById(R.id.imgBug2_2);
        ImageView imgBug2_3 = findViewById(R.id.imgBug2_3);
        ImageView imgBug2_4 = findViewById(R.id.imgBug2_4);
        ImageView imgBug3_1 = findViewById(R.id.imgBug3_1);
        ImageView imgBug3_2 = findViewById(R.id.imgBug3_2);
        ImageView imgBug3_3 = findViewById(R.id.imgBug3_3);
        ImageView imgBug3_4 = findViewById(R.id.imgBug3_4);
        ImageView imgBug4_1 = findViewById(R.id.imgBug4_1);
        ImageView imgBug4_2 = findViewById(R.id.imgBug4_2);
        ImageView imgBug4_3 = findViewById(R.id.imgBug4_3);
        ImageView imgBug4_4 = findViewById(R.id.imgBug4_4);
        imgBug1_1.setOnClickListener(this);
        imgBug1_2.setOnClickListener(this);
        imgBug1_3.setOnClickListener(this);
        imgBug1_4.setOnClickListener(this);
        imgBug2_1.setOnClickListener(this);
        imgBug2_2.setOnClickListener(this);
        imgBug2_3.setOnClickListener(this);
        imgBug2_4.setOnClickListener(this);
        imgBug3_1.setOnClickListener(this);
        imgBug3_2.setOnClickListener(this);
        imgBug3_3.setOnClickListener(this);
        imgBug3_4.setOnClickListener(this);
        imgBug4_1.setOnClickListener(this);
        imgBug4_2.setOnClickListener(this);
        imgBug4_3.setOnClickListener(this);
        imgBug4_4.setOnClickListener(this);
        bugs = new boolean[][] {{false, false, false, false},
                                {false, false, false, false},
                                {false, false, false, false},
                                {false, false, false, false}};
        timeMillisLeft = 10000;
        playGame();
    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgBug1_1:
                bugVisible(0,0, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{0,0})){
                    score++;
                    newBug();
                    bugs[0][0] = true;
                }
            case R.id.imgBug1_2:
                bugVisible(1,0, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{1,0})){
                    score++;
                    newBug();
                    bugs[1][0] = true;
                }
            case R.id.imgBug1_3:
                bugVisible(2,0, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{2,0})){
                    score++;
                    newBug();
                    bugs[2][0] = true;
                }
            case R.id.imgBug1_4:
                bugVisible(3,0, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{3,0})){
                    score++;
                    newBug();
                    bugs[3][0] = true;
                }
            case R.id.imgBug2_1:
                bugVisible(0,1, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{0,1})){
                    score++;
                    newBug();
                    bugs[0][1] = true;
                }
            case R.id.imgBug2_2:
                bugVisible(1,1, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{1,1})){
                    score++;
                    newBug();
                    bugs[1][1] = true;
                }
            case R.id.imgBug2_3:
                bugVisible(2,1, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{2,1})){
                    score++;
                    newBug();
                    bugs[2][1] = true;
                }
            case R.id.imgBug2_4:
                bugVisible(3,1, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{3,1})){
                    score++;
                    newBug();
                    bugs[3][1] = true;
                }
            case R.id.imgBug3_1:
                bugVisible(0,2, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{0,2})){
                    score++;
                    newBug();
                    bugs[0][2] = true;
                }
            case R.id.imgBug3_2:
                bugVisible(1,2, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{1,2})){
                    score++;
                    newBug();
                    bugs[1][2] = true;
                }
            case R.id.imgBug3_3:
                bugVisible(2,2, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{2,2})){
                    score++;
                    newBug();
                    bugs[2][2] = true;
                }
            case R.id.imgBug3_4:
                bugVisible(3,2, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{3,2})){
                    score++;
                    newBug();
                    bugs[3][2] = true;
                }
            case R.id.imgBug4_1:
                bugVisible(0,3, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{0,3})){
                    score++;
                    newBug();
                    bugs[0][3] = true;
                }
            case R.id.imgBug4_2:
                bugVisible(1,3, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{1,3})){
                    score++;
                    newBug();
                    bugs[1][3] = true;
                }
            case R.id.imgBug4_3:
                bugVisible(2,3, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{2,3})){
                    score++;
                    newBug();
                    bugs[2][3] = true;
                }
            case R.id.imgBug4_4:
                bugVisible(3,3, View.INVISIBLE);
                if (Arrays.equals(selectedBug,new int[]{3,3})){
                    score++;
                    newBug();
                    bugs[3][3] = true;
                }
        }
    }

    private void playGame() {

        CountDownTimer timer = new CountDownTimer(timeMillisLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeMillisLeft = l;
                updateTimer();
                if (score == TOTAL_BUGS) {
                    TextView txtSquashSuccess = findViewById(R.id.txtSmashBugsSuccess);
                    txtSquashSuccess.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
        newBug();
    }
    private void newBug() {
        //THE GAME DOESN"T WORK BECAUSE CLICKING THINGS DON'T ACTUALLY CLICK THEM
        Random r = new Random();
        int row = r.nextInt(4);
        int col = r.nextInt(4);
        while (bugs[row][col] == true) {
            row = r.nextInt(4);
            col = r.nextInt(4);
        }
        selectedBug = new int[]{row, col};
        bugVisible(row, col, View.VISIBLE);
        System.out.println(row + " " + col);
    }

    private void bugVisible(int row, int col, int visibleCode) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0:
                        ImageView bug1_1 = findViewById(R.id.imgBug1_1);
                        bug1_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug1_2 = findViewById(R.id.imgBug1_2);
                        bug1_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug1_3 = findViewById(R.id.imgBug1_3);
                        bug1_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug1_4 = findViewById(R.id.imgBug1_4);
                        bug1_4.setVisibility(visibleCode);
                        break;
                }
                break;
            case 1:
                switch (col) {
                    case 0:
                        ImageView bug2_1 = findViewById(R.id.imgBug2_1);
                        bug2_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug2_2 = findViewById(R.id.imgBug2_2);
                        bug2_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug2_3 = findViewById(R.id.imgBug2_3);
                        bug2_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug2_4 = findViewById(R.id.imgBug2_4);
                        bug2_4.setVisibility(visibleCode);
                        break;
                }
                break;
            case 2:
                switch (col) {
                    case 0:
                        ImageView bug3_1 = findViewById(R.id.imgBug3_1);
                        bug3_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug3_2 = findViewById(R.id.imgBug3_2);
                        bug3_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug3_3 = findViewById(R.id.imgBug3_3);
                        bug3_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug3_4 = findViewById(R.id.imgBug3_4);
                        bug3_4.setVisibility(visibleCode);
                        break;
                }
                break;
            case 3:
                switch (col) {
                    case 0:
                        ImageView bug4_1 = findViewById(R.id.imgBug4_1);
                        bug4_1.setVisibility(visibleCode);
                        break;
                    case 1:
                        ImageView bug4_2 = findViewById(R.id.imgBug4_2);
                        bug4_2.setVisibility(visibleCode);
                        break;
                    case 2:
                        ImageView bug4_3 = findViewById(R.id.imgBug4_3);
                        bug4_3.setVisibility(visibleCode);
                        break;
                    case 3:
                        ImageView bug4_4 = findViewById(R.id.imgBug4_4);
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

    private void endGame() {
        boolean success = false;
        if (score == TOTAL_BUGS) {
            success = true;
        }
        Intent intent = new Intent();
        intent.putExtra("success", success);
        setResult(RESULT_OK, intent);
        laySmashBugs.setVisibility(View.GONE);
        TextView txtSquashSuccess = findViewById(R.id.txtSmashBugsSuccess);
        txtSquashSuccess.setVisibility(View.INVISIBLE);
        finish();
    }
}
