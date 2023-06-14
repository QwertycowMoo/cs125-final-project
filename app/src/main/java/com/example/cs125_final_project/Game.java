package com.example.cs125_final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public abstract class Game extends AppCompatActivity {
    public CountDownTimer timer;
    public long timeMillisLeft;
    public TextView txtTimer;

    public void setupGame(){
        timer = new CountDownTimer(timeMillisLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer();
                timeMillisLeft = millisUntilFinished;
            }

            @Override
            public void onFinish() {

                endGame(RESULT_OK, checkGame());
            }
        }.start();
    }

    public abstract boolean checkGame();
    public void updateTimer() {
        txtTimer = findViewById(R.id.txtTimer);
        int seconds = (int) timeMillisLeft / 1000;
        txtTimer.setText("Time Left: " + seconds);
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
                        endGame(RESULT_CANCELED, false);
                    }
                })
                .setNegativeButton(R.string.continue_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        timer = new CountDownTimer(timeMillisLeft, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                timeMillisLeft = millisUntilFinished;
                                updateTimer();
                            }

                            @Override
                            public void onFinish() {
                                endGame(RESULT_OK, false);
                            }
                        }.start();
                    }
                });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

    public void endGame(int endCode, boolean success) {
        Intent intent = new Intent();
        intent.putExtra("success", success);
        setResult(endCode, intent);
        finish();
    }
}
