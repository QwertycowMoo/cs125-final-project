package com.example.cs125_final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class QuestionsGame extends AppCompatActivity {
    /** map storing the questions and answers as well as the correct answer */
    private Map<String[], Integer> questionsAnswer;
    private List<String[]> questionKeys;


    /**timer shtuff*/
    private CountDownTimer timer;
    private TextView txtTimer;
    private long timeMillisLeft;

    /**question shtuff*/
    String[] questionKey;
    private LinearLayout layQuestions;

    public static final int QUESTIONS_GAME_END_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        layQuestions = findViewById(R.id.layQuestion);
        layQuestions.setVisibility(View.VISIBLE);
        //The way I'm gonna map these questions and answers is the question will always be 0, and answers 1-4
        //The correct answer is the value of the map, and corresponds to the index of the correct answer in the key
        //So the index of the correct answer is the answer's index in the String array
        questionsAnswer = new HashMap<>();
        questionsAnswer.put(new String[]{"What is Geoff's Last Name?", "Chalen", "Challen", "Callen", "Challan"}, 2);

        questionKeys = new ArrayList<>(questionsAnswer.keySet());
        //setting up timer
        txtTimer = findViewById(R.id.txtTimer);
        timeMillisLeft = 6000;

        //Probably should put this in a static method somewhere but we need this to make it look good
        Intent intent = getIntent();
        int coins = intent.getIntExtra("coins", -1);
        int lives = intent.getIntExtra("lives", -1);
        TextView txtCoins = findViewById(R.id.txtCoins);
        TextView txtLives = findViewById(R.id.txtLives);
        txtCoins.setText("Coins Earned: " + coins);
        GameActivity.updateLives(lives, txtLives);

        //pick a question
        questionKey = questionKeys.get(new Random().nextInt(questionKeys.size()));
        TextView txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestion.setText(questionKey[0]);
        //Setting up the buttons for game
        Button btnAnswer1 = findViewById(R.id.btnAnswer1);
        Button btnAnswer2 = findViewById(R.id.btnAnswer2);
        Button btnAnswer3 = findViewById(R.id.btnAnswer3);
        Button btnAnswer4 = findViewById(R.id.btnAnswer4);
        btnAnswer1.setText(questionKey[1]);
        btnAnswer2.setText(questionKey[2]);
        btnAnswer3.setText(questionKey[3]);
        btnAnswer4.setText(questionKey[4]);

        btnAnswer1.setOnClickListener(v -> {
            answerClicked(1);
        });
        btnAnswer2.setOnClickListener(v -> {
            answerClicked(2);
        });
        btnAnswer3.setOnClickListener(v -> {
            answerClicked(3);
        });
        btnAnswer3.setOnClickListener(v -> {
            answerClicked(3);
        });
        playGame();
    }

    private void answerClicked(int pressed) {
        if (pressed == questionsAnswer.get(questionKey)) {
            endGame(RESULT_OK, true);
        } else {
            endGame(RESULT_OK, false);
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
                endGame(RESULT_OK, false);
            }
        }.start();

    }
    private void updateTimer() {
        int seconds = (int) timeMillisLeft / 1000;
        txtTimer.setText("Time Left: " + seconds);
    }

    private void endGame(int endCode, boolean success) {
        Intent intent = new Intent();
        intent.putExtra("success", success);
        setResult(endCode, intent);
        layQuestions.setVisibility(View.GONE);
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
                        endGame(RESULT_CANCELED, false);
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
