package my.edu.utar.individualv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Player extends AppCompatActivity {

    //private String successfulTouches;
    private int successfulTouches;
    private TextView successfulTouchesTextView;
    private SQLiteAdapter mySQLiteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //get score from level 5
        Intent intent = getIntent();
        successfulTouches = intent.getIntExtra("scorelevel5", 0);
        successfulTouchesTextView = findViewById(R.id.successfulTouchesTextView);
        successfulTouchesTextView.setText("Successful touches: " + successfulTouches);

        EditText PlayerName = findViewById(R.id.PlayerName);

        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();
        //mySQLiteAdapter.deleteAll();

        Button button = (Button) findViewById(R.id.submitbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mySQLiteAdapter.insert(PlayerName.getText().toString() + "\t\t", successfulTouches);

                Intent intent = new Intent(Player.this, Leaderboard.class);
                startActivity(intent);
            }
        });
    }
}