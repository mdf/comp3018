package com.example.pszmdf.martinintentservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    int jobNumber = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClickButton(View v) {
        Intent msgIntent = new Intent(this, CounterIntentService.class);
        msgIntent.putExtra("jobNumber", jobNumber);
        startService(msgIntent);
        jobNumber++;
    }
}
