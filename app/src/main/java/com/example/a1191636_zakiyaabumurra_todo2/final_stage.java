package com.example.a1191636_zakiyaabumurra_todo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class final_stage extends AppCompatActivity {

    private SQLiteDatabase db;
    private LinearLayout scoreLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_stage);

        scoreLayout = findViewById(R.id.scoreLayout);

        // Open the database for reading
        db = openOrCreateDatabase("scores.db", Context.MODE_PRIVATE, null);

        // Query the top 5 scores from the database
        Cursor cursor = db.rawQuery("SELECT * FROM scores ORDER BY score DESC LIMIT 5", null);

        // Update the UI with the scores
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            int score = cursor.getInt(2);

            TextView nameTextView = new TextView(this);
            nameTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            nameTextView.setText(name);
            scoreLayout.addView(nameTextView);

            TextView scoreTextView = new TextView(this);
            scoreTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            scoreTextView.setText(String.valueOf(score));
            scoreTextView.setGravity(Gravity.CENTER);
            scoreLayout.addView(scoreTextView);
        }

        cursor.close();
    }


    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(final_stage.this, "EXP4", null, 1);
        Cursor userScoreCursor = dataBaseHelper.getTopScores(5);
        scoreLayout.removeAllViews();
        while (userScoreCursor.moveToNext()) {
            TextView textView = new TextView(final_stage.this);
            textView.setText("Name= " + userScoreCursor.getString(0) + "\nScore= " + userScoreCursor.getString(1) + "\n\n");
            scoreLayout.addView(textView);
        }
    }

}