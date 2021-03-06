package com.example.android.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button bSingle;
    Button bMulti;
    Button bExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        bSingle = findViewById(R.id.bSingle);
        bMulti = findViewById(R.id.bMulti);
        bExit = findViewById(R.id.bExit);

        bSingle.setOnClickListener(this);
        bMulti.setOnClickListener(this);
        bExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bSingle:
                openSecondActivity();
                break;
            case R.id.bMulti:
                openMainActivity();
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

    public void openSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void appExit() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}