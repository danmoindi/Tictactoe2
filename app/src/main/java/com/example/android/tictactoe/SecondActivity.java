package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button b3Board;
    Button b5Board;
    Button bExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        b3Board = findViewById(R.id.b3Board);
        b5Board = findViewById(R.id.b5Board);
        bExit = findViewById(R.id.bExit);

        b3Board.setOnClickListener(this);
        b5Board.setOnClickListener(this);
        bExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.b3Board:
                openMainActivity();
                break;
            case R.id.b5Board:
                openFiveActivity();
                break;
            case R.id.bExit:
                appExit();
                break;
        }
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openFiveActivity() {
        Intent intent = new Intent(this, FiveActivity.class);
        startActivity(intent);
    }

    public void appExit() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}