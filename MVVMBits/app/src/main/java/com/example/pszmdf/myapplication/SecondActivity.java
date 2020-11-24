package com.example.pszmdf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    SecondViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        model = new ViewModelProvider(this).get(SecondViewModel.class);

        model.someState.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.d("g53mdp", "onPropertyChanged");
                TextView textView = findViewById(R.id.textView1);
                textView.setText(model.someState.get());
            }
        });







        new Thread(()->{
            try {
                while(true) {
                    Thread.sleep(2000);
                    runOnUiThread(() -> {
                        EditText editText = findViewById(R.id.editText1);
                        model.someState.set(editText.getText().toString() + " " + Math.random());
                    });
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void doSomething(View v) {
        EditText editText = findViewById(R.id.editText1);
        model.someState.set(editText.getText().toString());
    }
}