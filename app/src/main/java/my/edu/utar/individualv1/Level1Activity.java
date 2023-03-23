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

public class Level1Activity extends AppCompatActivity {

    private View[] views;
    private int highlightedIndex;
    private int successfulTouches;
    private TextView successfulTouchesTextView;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        // Initialize variables
        views = new View[] {
                findViewById(R.id.view1), findViewById(R.id.view2),
                findViewById(R.id.view3), findViewById(R.id.view4)
        };
        highlightedIndex = -1;
        successfulTouches = 0;
        successfulTouchesTextView = findViewById(R.id.successfulTouchesTextView);

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
                // Proceed to Level 2
                Intent intent = new Intent(Level1Activity.this, Level2Activity.class);
                intent.putExtra("Level", 1);
                intent.putExtra("scorelevel1", successfulTouches);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();

        Button exitbutton = (Button) findViewById(R.id.exitbutton);
        exitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                countDownTimer.cancel();
                Intent intent = new Intent(Level1Activity.this, MainActivity.class);
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