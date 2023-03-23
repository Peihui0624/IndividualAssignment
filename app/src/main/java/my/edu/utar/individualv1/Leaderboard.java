package my.edu.utar.individualv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Leaderboard extends AppCompatActivity {

    private SQLiteAdapter mySQLiteAdapter;
    private TextView FinalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToRead();

        String result = mySQLiteAdapter.queueByScore();
        mySQLiteAdapter.close();

        //TextView tv = new TextView(this);
        FinalTextView = findViewById(R.id.textView4);
        FinalTextView.setText(result);
        //tv.setText(result);
        FinalTextView.setTextSize(20);

    }
}