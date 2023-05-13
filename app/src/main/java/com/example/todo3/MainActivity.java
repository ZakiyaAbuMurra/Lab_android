package com.example.todo3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // Declaration of AnimationDrawable and Button objects
    // and ImageView
    private AnimationDrawable isAnimation;
    private Button btn;
    private ImageView img;


    private boolean isStart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img = findViewById(R.id.imageView);
        btn = findViewById(R.id.start);
        img.setImageResource(R.drawable.animation);
        isAnimation = (AnimationDrawable)img.getDrawable();
        btn.setBackgroundColor(Color.GREEN);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view)
            {
                if (!isStart) {
                    isAnimation.start();
                    btn.setText("stop");
                    isStart = true;
                    btn.setBackgroundColor(Color.RED);
                }
                else {

                    isAnimation.stop();

                    btn.setText("Start");

                    isStart = false;

                    btn.setBackgroundColor(Color.GREEN);
                }
            }
        });
    }
}