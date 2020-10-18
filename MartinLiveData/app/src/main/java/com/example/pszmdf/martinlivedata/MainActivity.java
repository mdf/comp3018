package com.example.pszmdf.martinlivedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyViewModel model;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new ViewModelProvider(this).get(MyViewModel.class);

        textView = findViewById(R.id.textView);

        final Observer<Integer> waveObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer i) {
                Log.d("g53mdp", "onChanged");
                textView.setText("waves: " + i);
            }
        };

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.incrementWaves();
            }
        });

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getWaves().observe(this, waveObserver);

        new Thread(new Runnable() {

            public void run() {
                int i = 0;
                while(true) {
                    Log.d("g53mdp", "waiting " + i++);
                    model.incrementWaves();
                    try{Thread.sleep(2000);}catch(Exception e){;}
                }
            }
        }).start();

    }
}