package com.example.a1191636_zakiyaabumurra_todo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Level1 extends AppCompatActivity {
    public  int counter ;
    private int num1, num2, correct_answer, score;
    private TextView scoreText , mTimeLeftTextView ;
    private MaterialButton num1Button, num2Button, result1Button, result2Button, result3Button;

    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        num1Button = findViewById(R.id.num1Button);
        num2Button = findViewById(R.id.num2Button);
        result1Button = findViewById(R.id.result1Button);
        result2Button = findViewById(R.id.result2Button);
        result3Button = findViewById(R.id.result3Button);
        scoreText = findViewById(R.id.scoreText);
        mTimeLeftTextView = (TextView) findViewById(R.id.timerTextView);

        result1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(result1Button.getText().toString());
            }
        });

        result2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(result2Button.getText().toString());
            }
        });

        result3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(result3Button.getText().toString());
            }
        });
        numbers();

        // Declare the timer
        mTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                mTimeLeftTextView.setText("Timer :"+f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }

            @Override
            public void onFinish() {
                // Perform the necessary actions after the timer has expired
                mTimeLeftTextView.setText("00:00:00");
                Intent intent = new Intent(Level1.this, Level_2.class);
                //send the score value from one activity to another
                 String s = scoreText.getText().toString() ;
                 intent.putExtra("score", s);
                 intent.putExtra("playerName" , getIntent().getStringExtra("playerName"));
                 startActivity(intent);
            }
        }.start();
    }

    private void numbers() {
        Random random = new Random();
        num1 = random.nextInt(10) + 1;
        num2 = random.nextInt(10) + 1;
        int incorrect_answer_1 = -1;
        int incorrect_ansewr_2 = -1;
        int correctButton = random.nextInt(3);
        correct_answer = num1 + num2;
        num1Button.setText(String.valueOf(num1));
        num2Button.setText(String.valueOf(num2));

        switch (correctButton) {
            case 0:
                result1Button.setText(String.valueOf(correct_answer));
                break;
            case 1:
                result2Button.setText(String.valueOf(correct_answer));
                break;
            case 2:
                result3Button.setText(String.valueOf(correct_answer));
                break;
        }
        //Fill the other two buttons with random integers that are not equal to the correct result.
        while (incorrect_answer_1 == -1 || incorrect_answer_1 == correct_answer) {
            incorrect_answer_1 = random.nextInt(19) + 1;
        }
        while (incorrect_ansewr_2 == -1 || incorrect_ansewr_2 == correct_answer || incorrect_ansewr_2 == incorrect_answer_1) {
            incorrect_ansewr_2 = random.nextInt(19) + 1;
        }
        if (correctButton == 0) {
            result2Button.setText(String.valueOf(incorrect_answer_1));
            result3Button.setText(String.valueOf(incorrect_ansewr_2));
        } else if (correctButton == 1) {
            result1Button.setText(String.valueOf(incorrect_answer_1));
            result3Button.setText(String.valueOf(incorrect_ansewr_2));
        } else {
            result1Button.setText(String.valueOf(incorrect_answer_1));
            result2Button.setText(String.valueOf(incorrect_ansewr_2));
        }
    }


    private void checkAnswer(String answer) {
        int selectedAnswer = Integer.parseInt(answer);
        if (selectedAnswer == correct_answer) {
            score++;
            scoreText.setText("Score: " + score);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            score--;
            scoreText.setText("Score: " + score);
            Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show();
        }
        numbers();
    }




}