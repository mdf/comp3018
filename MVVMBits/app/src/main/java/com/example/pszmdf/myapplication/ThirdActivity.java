package com.example.pszmdf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    ThirdViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        model = new ViewModelProvider(this).get(ThirdViewModel.class);

        final Observer<String> stateObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("g53mdp", "onPropertyChanged");
                TextView textView = findViewById(R.id.textView1);
                textView.setText(model.someState.getValue());
            }
        };

        model.someState.observe(this, stateObserver);

        new Thread(()->{
            try {
                while(true) {
                    Thread.sleep(2000);
                    runOnUiThread(() -> {
                        EditText editText = findViewById(R.id.editText1);
                        model.someState.setValue(editText.getText().toString() + " " + Math.random());
                    });
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void doSomething(View v) {
        EditText editText = findViewById(R.id.editText1);
        model.someState.setValue(editText.getText().toString());
    }
}