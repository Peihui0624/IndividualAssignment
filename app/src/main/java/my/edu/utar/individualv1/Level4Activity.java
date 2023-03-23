package my.edu.utar.individualv1;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Level4Activity extends AppCompatActivity {

    private View[] views;
    private int highlightedIndex;
    private int successfulTouches;
    private TextView successfulTouchesTextView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);

        // Initialize variables
        views = new View[] {
                findViewById(R.id.view30), findViewById(R.id.view31), findViewById(R.id.view32),
                findViewById(R.id.view33), findViewById(R.id.view34), findViewById(R.id.view35),
                findViewById(R.id.view36), findViewById(R.id.view37), findViewById(R.id.view38),
                findViewById(R.id.view39), findViewById(R.id.view40), findViewById(R.id.view41),
                findViewById(R.id.view42), findViewById(R.id.view43), findViewById(R.id.view44),
                findViewById(R.id.view45), findViewById(R.id.view46), findViewById(R.id.view47),
                findViewById(R.id.view48), findViewById(R.id.view49), findViewById(R.id.view50),
                findViewById(R.id.view51), findViewById(R.id.view52), findViewById(R.id.view53),
                findViewById(R.id.view54)
        };
        highlightedIndex = -1;
        //get score from level 3
        Intent intent = getIntent();
        successfulTouches = intent.getIntExtra("scorelevel3", 0);
        successfulTouchesTextView = findViewById(R.id.successfulTouchesTextView);
        successfulTouchesTextView.setText("Successful touches: " + successfulTouches);

        // Randomly highlight a view
        highlightRandomView();

        // Set up touch listeners for views
        for (View view : views) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (v == views[highlightedIndex]) {
                        // Player touched the highlighted view
                        successfulTouches++;
                        successfulTouchesTextView.setText("Scores: " + successfulTouches);
                        highlightRandomView();
                    }
                    return true;
                }
            });
        }

        // Set up countdown timer
        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                // Proceed to Level 5
                Intent intent = new Intent(Level4Activity.this, Level5Activity.class);
                intent.putExtra("Level", 4);
                intent.putExtra("scorelevel4", successfulTouches);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();

        Button exitbutton = (Button) findViewById(R.id.exitbutton);
        exitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent intent = new Intent(Level4Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void highlightRandomView() {
        // Remove highlighting from previous view
        if (highlightedIndex != -1) {
            views[highlightedIndex].setBackgroundColor(Color.RED);
        }
        // Randomly highlight a new view that is different from the previous view
        int randomIndex = highlightedIndex;
        while (randomIndex == highlightedIndex) {
            randomIndex = (int) (Math.random() * views.length);
        }
        highlightedIndex = randomIndex;
        views[highlightedIndex].setBackgroundColor(Color.BLUE);
    }
}