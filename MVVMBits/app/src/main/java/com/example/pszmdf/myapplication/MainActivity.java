package com.example.pszmdf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAct(View v) {

        switch(v.getId()) {
            case R.id.act1:
                Log.d("g53mdp", "activity 1");
                startActivity(new Intent(MainActivity.this, FirstActivity.class));
                break;
            case R.id.act2:
                Log.d("g53mdp", "activity 2");
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.act3:
                Log.d("g53mdp", "activity 3");
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;
            case R.id.act4:
                Log.d("g53mdp", "activity 4");
                startActivity(new Intent(MainActivity.this, ForthActivity.class));
                break;
        }
    }
}