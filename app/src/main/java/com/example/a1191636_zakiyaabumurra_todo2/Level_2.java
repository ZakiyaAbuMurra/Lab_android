package com.example.a1191636_zakiyaabumurra_todo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;




public class Level_2 extends AppCompatActivity {



        private final Random random = new Random();
        private CountDownTimer mTimer;

        private final Button[] buttons = new Button[3];
        String correct_answer;
        int score ;
        HashMap<String, String> dictionary ;
        TextView scoreTextView , arabicTextView ,  mTimeLeftTextView;

        List<String> arabicWords = new ArrayList<>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_level2);

            scoreTextView = (TextView) findViewById(R.id.scoreText);
            arabicTextView = (TextView) findViewById(R.id.arabicButton);
            buttons[0] = (Button) findViewById(R.id.result1Button);
            buttons[1] = (Button) findViewById(R.id.result2Button);
            buttons[2] = (Button) findViewById(R.id.result3Button);
            mTimeLeftTextView = (TextView) findViewById(R.id.timerTextView_2);

            try {
                dictionary = readFile(getApplicationContext(), "dict.txt");
                arabicWords.addAll(dictionary.keySet());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            buttons[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(buttons[0].getText().toString());
                }
            });

            buttons[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnswer(buttons[1].getText().toString());
                }
            });

            buttons[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(buttons[2].getText().toString());
                }
            });
            generateMeaning();




            // Declare the timer
            mTimer = new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                  mTimeLeftTextView.setText("Timer :" + f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                }

                @Override
                public void onFinish() {
                    // Perform the necessary actions after the timer has expired
                    mTimeLeftTextView.setText("00:00:00");

                    //send the score value from one activity to another
                    int allGamesScore = getIntent().getIntExtra("score", 0);
                    int currentScore = Integer.parseInt(scoreTextView.getText().toString().substring(scoreTextView.getText().toString().indexOf(":") + 1));
                    allGamesScore += currentScore;
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Level_2.this, "1191636_ZakiyaAbuMurra_TODO2", null, 1);
                    Player userScore = new Player();
                    userScore.setScore(Integer.parseInt(String.valueOf(allGamesScore)));
                    userScore.setNickname(getIntent().getStringExtra("playerName"));
                    dataBaseHelper.insertScore(userScore);
                    dataBaseHelper.getTopScores(5);
                    Intent intent = new Intent(Level_2.this, final_stage.class);
                    startActivity(intent);
                    finish();
                }
            }.start();

        }


        public static HashMap<String, String> readFile(Context context, String filename) throws IOException {
            HashMap<String, String> hashMap = new HashMap<>();
            InputStream inputStream = context.getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\t");
                hashMap.put(tokens[1], tokens[0]);
            }
            br.close();
            inputStream.close();
            return hashMap;
        }




        private void generateMeaning() {
            int firstButtonIndex, secondButtonIndex;
            String firstWord,secondWord;
            do {
                firstButtonIndex = random.nextInt(3);
                secondButtonIndex = random.nextInt(3);
            } while (firstButtonIndex == secondButtonIndex);
            do {
                firstWord = arabicWords.get(random.nextInt(arabicWords.size()));
                secondWord = arabicWords.get(random.nextInt(arabicWords.size()));
                correct_answer = arabicWords.get(random.nextInt(arabicWords.size()));

            } while (Objects.equals(firstWord, secondWord) || Objects.equals(firstWord, correct_answer) || Objects.equals(secondWord, correct_answer));

            // Set the button texts
            arabicTextView.setText(correct_answer);
            buttons[firstButtonIndex].setText(dictionary.get(firstWord));
            buttons[secondButtonIndex].setText(dictionary.get(secondWord));
            buttons[3 - firstButtonIndex - secondButtonIndex].setText(dictionary.get(correct_answer));
        }



    private void checkAnswer(String answer) {
        String selectedAnswer = answer ;
        if (selectedAnswer.equals(dictionary.get(correct_answer))) {
            score++;
            scoreTextView.setText("Score:" + score);
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            score--;
            scoreTextView.setText("Score:" + score);
            Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show();
        }
        generateMeaning();
    }


}

