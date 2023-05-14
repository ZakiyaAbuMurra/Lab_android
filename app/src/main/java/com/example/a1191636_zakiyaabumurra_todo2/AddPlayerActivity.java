package com.example.a1191636_zakiyaabumurra_todo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPlayerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        final EditText nickname = (EditText) findViewById(R.id.nicknameEditText);
        final Button start = (Button) findViewById(R.id.startButton);

        // database for reading our database.
        DataBaseHelper dbHelper = new DataBaseHelper(this, " 1191636_ZakiyaAbuMurra_TODO2 ", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player player = new Player();

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nickname2 = nickname.getText().toString();
                        if (nickname.getText().toString().isEmpty()) player.setNickname("No name");
                        else player.setNickname(nickname.getText().toString());

                        Intent intent = new Intent(AddPlayerActivity.this, Level1.class);
                        intent.putExtra("playerName" , nickname2);
                        AddPlayerActivity.this.startActivity(intent);
                    }
                });
            };
        })
    ;}
}
