package com.example.pszmdf.martinsimpleservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStartService(View v) {
        this.startService(new Intent(MainActivity.this, SimpleService.class));
    }

    public void onClickStopService(View v) {
        this.stopService(new Intent(MainActivity.this, SimpleService.class));
    }
}
