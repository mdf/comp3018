package com.example.pszmdf.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    FirstViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        model = new ViewModelProvider(this).get(FirstViewModel.class);

        TextView textView = findViewById(R.id.textView1);
        textView.setText(model.getSomeState());
    }

    public void doSomething(View v) {

        EditText editText = findViewById(R.id.editText1);
        model.setSomeState(editText.getText().toString());

        TextView textView = findViewById(R.id.textView1);
        textView.setText(model.getSomeState());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        model.setSomeState("whatever I got out of the bundle");
    }
}