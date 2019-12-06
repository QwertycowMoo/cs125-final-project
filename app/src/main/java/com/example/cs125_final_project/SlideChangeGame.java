package com.example.cs125_final_project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Random;
public class SlideChangeGame extends AppCompatActivity {
    /**Array of all the slide images used to change/match */
    private int[] slideImages;
    /**Geoff's slide, the one to match: an index*/
    private int geoffSlide;
    /**Your current slide, change this to match Geoff's: an index*/
    private int currentSlide;
    /**CountDown Timer */
    private CountDownTimer timer;
    /**Number of seconds for the timer*/
    private long timeMillisLeft;
    /**They're global because it works that way*/
    private LinearLayout layChangeSlides;
    private ImageView imgYours;
    private ImageView imgGeoff;
    private TextView txtTimer;

    public static final int SLIDE_CHANGE_GAME_END_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Get all the images
        slideImages = new int[]{R.drawable.slide_big_o, R.drawable.slide_code_ex,
                R.drawable.slide_code_ex_trees_recur, R.drawable.slide_list_documentation,
                R.drawable.slide_questions, R.drawable.slides_2linklist, R.drawable.slides_announcements,
                R.drawable.slides_arr_vs_link, R.drawable.slides_im_sorry, R.drawable.slides_qck_sort,
                R.drawable.slides_static_methods, R.drawable.slides_tree};

        //Set the imageviews to a slide
        Random r = new Random();
        geoffSlide = r.nextInt(slideImages.length);
        System.out.println("initializing geoffSlide to: " + geoffSlide);
        currentSlide = geoffSlide;
        while (currentSlide == geoffSlide) {
            currentSlide = r.nextInt(slideImages.length);
        }

        imgGeoff = findViewById(R.id.imgGeoffSlide);
        imgYours = findViewById(R.id.imgYourSlide);
        imgGeoff.setImageResource(slideImages[geoffSlide]);
        imgYours.setImageResource(slideImages[currentSlide]);
        layChangeSlides = findViewById(R.id.layChangeSlides);
        layChangeSlides.setVisibility(View.VISIBLE);
        //Timer Setup
        txtTimer = findViewById(R.id.txtTimer);
        timeMillisLeft = 10000;

        //Updating coins and lives has to happen through intent or else it just breaks
        Intent intent = getIntent();
        int coins = intent.getIntExtra("coins", -1);
        int lives = intent.getIntExtra("lives", -1);
        TextView txtCoins = findViewById(R.id.txtCoins);
        TextView txtLives = findViewById(R.id.txtLives);
        txtCoins.setText("Coins Earned: " + coins);
        GameActivity.updateLives(lives, txtLives);



        //Update Game Title
        TextView txtGameTitle = findViewById(R.id.txtGameTitle);
        txtGameTitle.setText("Match the Slide!");
        playGame();

    }


    public void playGame() {
        timer = new CountDownTimer(timeMillisLeft, 1000) {
            @Override
            public void onTick(long l) {
                timeMillisLeft = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
        //This needs to be declared in the OnCreate first then it can be used, maybe global variable?
        //Makes the layout appear so the player can see it
        //Starts Geoff's slide on a random slide and the player on a random slide
        Button btnNext = findViewById(R.id.btnNextSlide);
        Button btnPrev = findViewById(R.id.btnPrevSlide);
        btnNext.setOnClickListener(v -> {
            if (currentSlide < slideImages.length - 1) {
                currentSlide += 1;
                imgYours.setImageResource(slideImages[currentSlide]);
            } else {
                currentSlide = 0;
                imgYours.setImageResource(slideImages[currentSlide]);
            }
        });

        btnPrev.setOnClickListener(v -> {
            if (currentSlide > 0 ) {
                currentSlide -= 1;
                imgYours.setImageResource(slideImages[currentSlide]);
            } else {
                currentSlide = slideImages.length - 1;
                imgYours.setImageResource(slideImages[currentSlide]);
            }
        });
    }

    private void updateTimer() {
        int seconds = (int) timeMillisLeft / 1000;
        txtTimer.setText("Time Left: " + seconds);
    }

    private void endGame() {
        boolean success = false;
        if (currentSlide == geoffSlide) {
            success = true;
        }
        Intent intent = new Intent();
        intent.putExtra("success", success);
        setResult(RESULT_OK, intent);
        layChangeSlides.setVisibility(View.GONE);
        finish();
    }

}
